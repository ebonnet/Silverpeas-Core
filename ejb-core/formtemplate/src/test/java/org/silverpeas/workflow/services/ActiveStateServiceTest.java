package org.silverpeas.workflow.services;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ActiveStateServiceTest {

  /**
   * Default constructor
   */
  public ActiveStateServiceTest() {
  }

  private static ReplacementDataSet dataSet;

  @BeforeClass
  public static void prepareDataSet() throws Exception {
    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
    dataSet = new ReplacementDataSet(builder.build(ProcessInstanceService.class.getClassLoader().
            getResourceAsStream("org/silverpeas/workflow/services/activestate_dataset.xml")));
    dataSet.addReplacementObject("[NULL]", null);
  }
  @Inject
  private ActiveStateService asService;

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
  public void testGetActiveState() {
    Integer firstInstance = 1;
    ActiveStateMdl asResult = asService.getActiveState(firstInstance);
    assertThat(asResult, is(notNullValue()));
    assertThat(asResult.getState(), is("VALID"));
    Integer secondInstance = 2;
    asResult = asService.getActiveState(secondInstance);
    assertThat(asResult, is(notNullValue()));
    assertThat(asResult.getState(), is("WORK_IN_PROGRESS"));
  }
  

  @Test
  public void testUpdateActiveState() {
    Integer firstInstance = 1;
    ActiveStateMdl asResult = asService.getActiveState(firstInstance);
    assertThat(asResult, is(notNullValue()));
    assertThat(asResult.getState(), is("VALID"));
    asResult.setState("NEW_STATE");
    //Calendar calendar = Calendar.getInstance();
    Date timeoutDate = new Date();
    try {
      timeoutDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2012-12-31 10:45:30");
    } catch (ParseException e) {
    }
    asResult.setTimeoutDate(timeoutDate);
    asService.updateActiveState(asResult);
    asResult = asService.getActiveState(firstInstance);
    assertThat(asResult.getState(), is("NEW_STATE"));
    assertThat(asResult.getTimeoutDate(), is(timeoutDate));
  }

  @Test
  public void testDeleteActiveState() {
    Integer firstInstance = 1;
    ActiveStateMdl asResult = asService.getActiveState(firstInstance);
    assertThat(asResult, is(notNullValue()));
    asService.deleteActiveState(asResult);
    asResult = asService.getActiveState(firstInstance);
    assertThat(asResult, is(nullValue()));
  }


}
