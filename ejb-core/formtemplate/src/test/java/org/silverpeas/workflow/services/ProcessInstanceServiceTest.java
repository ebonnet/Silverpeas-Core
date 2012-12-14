package org.silverpeas.workflow.services;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
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
import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
  private ProcessInstanceService service;
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
  public void testGetListProcessInstance() {
    String instanceId = "workflow-test10";
    String userId = "10";
    List<ProcessInstanceMdl> processResults = service.getListProcessInstance(userId);
    assertThat(processResults, is(notNullValue()));
    assertThat(processResults, hasSize(1));
  }

}
