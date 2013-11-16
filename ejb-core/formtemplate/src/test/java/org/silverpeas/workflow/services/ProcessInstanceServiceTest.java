package org.silverpeas.workflow.services;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.silverpeas.workflow.model.ActiveStateMdl;
import org.silverpeas.workflow.model.InterestedUserMdl;
import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.workflow.engine.instance.ProcessInstanceImpl;
import com.stratelia.webactiv.util.DBUtil;

/**
 * @author ebonnet
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-formtemplate-datasource.xml",
    "/spring-formtemplate.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "jpaTransactionManager")
public class ProcessInstanceServiceTest {

  /**
   * Default constructor
   */
  public ProcessInstanceServiceTest() {
  }

  private static ReplacementDataSet dataSet;

  @BeforeClass
  public static void prepareDataSet() throws Exception {
    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
    dataSet = new ReplacementDataSet(builder.build(ProcessInstanceService.class.getClassLoader().
        getResourceAsStream("org/silverpeas/workflow/services/processinstance_dataset.xml")));
    dataSet.addReplacementObject("[NULL]", null);
  }

  @Inject
  private ProcessInstanceService processService;

  @Inject
  private InterestedUserService iuService;

  @Inject
  @Named("jpaDataSource")
  private DataSource dataSource;

  public Connection getConnection() throws SQLException {
    return this.dataSource.getConnection();
  }

  @Before
  public void generalSetUp() throws Exception {
    IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
    DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    DBUtil.getInstanceForTest(dataSource.getConnection());
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetListSupervisorProcessInstance() {
    String applicationId = "workflow-test10";
    List<ProcessInstanceMdl> processResults =
        processService.getListSupervisorProcessInstance(applicationId);
    assertThat(processResults, is(notNullValue()));
    assertThat(processResults, hasSize(3));

  }

  @Test
  public void testGetListUserProcessInstance() {
    String applicationId = "workflow-test10";
    String userId = "10";
    String role = "user";
    String[] userRoles = {"user","manager"};
    String[] userGroupIds = null;

    List<ProcessInstanceMdl> processResults =
        processService.getListUserProcessInstance(applicationId, userId, role, userRoles,
            userGroupIds);
    assertThat(processResults, is(notNullValue()));

    List<ProcessInstanceImpl> expectedResults =
        oldJDOPreparedStatment(applicationId, userId, role, userRoles, userGroupIds);
    System.out.println("expectedResults size = " + expectedResults.size());
    assertThat(processResults, hasSize(expectedResults.size()));

    // List<InterestedUserMdl> interestedUsers = processResults.get(0).getInterestedUsers();
    //
    // assertThat(interestedUsers, is(notNullValue()));
    // assertThat(interestedUsers, hasSize(2));
  }

  /**
   * @param applicationId
   * @param userId
   * @param role
   * @param userRoles
   * @param userGroupIds
   * @return
   */
  private List<ProcessInstanceImpl> oldJDOPreparedStatment(String applicationId, String userId,
      String role, String[] userRoles, String[] userGroupIds) {
    List<ProcessInstanceImpl> expectedResults = new ArrayList<ProcessInstanceImpl>();

    StringBuilder selectQuery = new StringBuilder();
    selectQuery.append("select *").append(" from SB_Workflow_ProcessInstance I ");
    selectQuery.append("where I.modelId = ? ");
    selectQuery.append("and exists (");
    selectQuery
        .append(
        "select instanceId from SB_Workflow_InterestedUser intUser where I.instanceId = intUser.instanceId and (");
    selectQuery.append("intUser.userId = ? ");
    if ((userRoles != null) && (userRoles.length > 0)) {
      selectQuery.append(" or intUser.usersRole in (");
      selectQuery.append(getSQLClauseIn(userRoles));
      selectQuery.append(")");
    }
    if (userGroupIds != null && userGroupIds.length > 0) {
      selectQuery.append(" or (intUser.groupId is not null");
      selectQuery.append(" and intUser.groupId in (");
      selectQuery.append(getSQLClauseIn(userGroupIds));
      selectQuery.append("))");
    }
    selectQuery.append(") and intUser.role = ? ");
    selectQuery.append("union ");
    selectQuery
        .append(
        "select instanceId from SB_Workflow_WorkingUser wkUser where I.instanceId = wkUser.instanceId and (");
    selectQuery.append("wkUser.userId = ? ");
    if ((userRoles != null) && (userRoles.length > 0)) {
      selectQuery.append(" or wkUser.usersRole in (");
      selectQuery.append(getSQLClauseIn(userRoles));
      selectQuery.append(")");
    }
    if (userGroupIds != null && userGroupIds.length > 0) {
      selectQuery.append(" or (wkUser.groupId is not null");
      selectQuery.append(" and wkUser.groupId in (");
      selectQuery.append(getSQLClauseIn(userGroupIds));
      selectQuery.append("))");
    }
    selectQuery.append(") and ");

    // role can be multiple (e.g: "role1,role2,...,roleN")
    selectQuery.append("( wkUser.role = ? ");
    selectQuery.append(" or wkUser.role like ? ");
    selectQuery.append(" or wkUser.role like ? ");
    selectQuery.append(" or wkUser.role like ? ");
    selectQuery.append(")");

    selectQuery.append(")");
    selectQuery.append("order by I.instanceId desc");

    try {
      Connection con = getConnection();
      PreparedStatement prepStmt = null;
      ResultSet rs = null;

      prepStmt = con.prepareStatement(selectQuery.toString());
      prepStmt.setString(1, applicationId);
      prepStmt.setString(2, userId);
      prepStmt.setString(3, role);
      prepStmt.setString(4, userId);
      prepStmt.setString(5, role);
      prepStmt.setString(6, "%," + role);
      prepStmt.setString(7, role + ",%");
      prepStmt.setString(8, "%," + role + ",%");
      rs = prepStmt.executeQuery();

      while (rs.next()) {
        ProcessInstanceImpl instance = new ProcessInstanceImpl();
        instance.setInstanceId(rs.getString(1));
        instance.setModelId(rs.getString(2));
        instance.setLockedByAdmin(rs.getBoolean(3));
        instance.setErrorStatus(rs.getBoolean(4));
        instance.setTimeoutStatus(rs.getBoolean(5));
        expectedResults.add(instance);
      }
    } catch (SQLException e) {
    }
    return expectedResults;
  }

  @Test
  public void testGetListProcessInstance() {
    String applicationId = "workflow-test10";
    String userId = "10";
    List<ProcessInstanceMdl> processResults =
        processService.getListProcessInstance(applicationId, userId);
    assertThat(processResults, is(notNullValue()));
    assertThat(processResults, hasSize(1));
    List<InterestedUserMdl> interestedUsers = processResults.get(0).getInterestedUsers();

    assertThat(interestedUsers, is(notNullValue()));
    assertThat(interestedUsers, hasSize(2));
  }

  @Test
  public void testGetProcessInstance() {
    Integer piId = 1;
    ProcessInstanceMdl processInstanceResult = processService.getProcessInstance(piId);
    assertThat(processInstanceResult, is(notNullValue()));
    ActiveStateMdl curActiveState = processInstanceResult.getActiveState();
    // ProcessInstanceMdl expResult = new ProcessInstanceMdl();

  }

  @Test
  public void testDeleteProcessInstance() {
    Integer piId = 1;
    ProcessInstanceMdl processInstanceResult = processService.getProcessInstance(piId);
    processService.deleteProcessInstance(processInstanceResult);
    processInstanceResult = processService.getProcessInstance(piId);
    assertThat(processInstanceResult, is(nullValue()));
  }

  @Test
  public void testAddInterestedUser() {
    Integer piId = 1;
    ProcessInstanceMdl processInstanceResult = processService.getProcessInstance(piId);
    processInstanceResult.addInterestedUser(new InterestedUserMdl("12", processInstanceResult,
        "TEST", "tester", null, null));

    List<InterestedUserMdl> interestedUsersResult =
        iuService.getListInterestedUser(processInstanceResult.getInstanceId());
    assertThat(interestedUsersResult, is(notNullValue()));
    assertThat(interestedUsersResult, hasSize(3));
  }

  /**
   * Regression testing purpose
   * @param items
   * @return a string with all the items separated by a comma ','
   */
  private String getSQLClauseIn(String[] items) {
    StringBuffer result = new StringBuffer();
    boolean first = true;
    for (String item : items) {
      if (!first) {
        result.append(", ");
      }
      result.append("'").append(item).append("'");
      first = false;
    }
    return result.toString();
  }

}
