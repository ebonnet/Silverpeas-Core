package org.silverpeas.workflow.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.silverpeas.workflow.services.ProcessInstanceService;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;
import com.silverpeas.workflow.api.WorkflowException;
import com.silverpeas.workflow.api.instance.Actor;
import com.silverpeas.workflow.api.instance.HistoryStep;
import com.silverpeas.workflow.api.instance.ProcessInstance;
import com.silverpeas.workflow.api.model.State;
import com.silverpeas.workflow.api.user.User;

@Service
@Transactional
public class ProcessInstanceMdlManagerImpl implements ProcessInstanceMdlManager, Serializable {

  private static final long serialVersionUID = 8602128812691905672L;

  @Inject
  private ProcessInstanceService processInstanceService;
  
  
  @Override
  public List<ProcessInstanceMdl> getProcessInstances(String peasId, User user, String role)
      throws WorkflowException {
    return getProcessInstances(peasId, user, role, null, null);
  }

  @Override
  public List<ProcessInstanceMdl> getProcessInstances(String applicationId, User user, String role,
      String[] userRoles, String[] groupIds) throws WorkflowException {
    List<ProcessInstanceMdl> processInstances = new ArrayList<ProcessInstanceMdl>();
    if ("supervisor".equals(role)) {
      processInstances = processInstanceService.getListSupervisorProcessInstance(applicationId);
    } else {
      processInstances = processInstanceService.getListUserProcessInstance(applicationId, user.getUserId(), role, userRoles, groupIds);
    }
    
    return processInstances;

//    if (role.equals("supervisor")) {
//      selectQuery.append("select * from SB_Workflow_ProcessInstance instance where modelId = ?");
//      prepStmt = con.prepareStatement(selectQuery.toString());
//      prepStmt.setString(1, peasId);
//    } else {
//      selectQuery.append("select ").append(COLUMNS)
//          .append(" from SB_Workflow_ProcessInstance I ");
//      selectQuery.append("where I.modelId = ? ");
//      selectQuery.append("and exists (");
//      selectQuery
//          .append(
//          "select instanceId from SB_Workflow_InterestedUser intUser where I.instanceId = intUser.instanceId and (");
//      selectQuery.append("intUser.userId = ? ");
//      if ((userRoles != null) && (userRoles.length > 0)) {
//        selectQuery.append(" or intUser.usersRole in (");
//        selectQuery.append(getSQLClauseIn(userRoles));
//        selectQuery.append(")");
//      }
//      if (userGroupIds != null && userGroupIds.length > 0) {
//        selectQuery.append(" or (intUser.groupId is not null");
//        selectQuery.append(" and intUser.groupId in (");
//        selectQuery.append(getSQLClauseIn(userGroupIds));
//        selectQuery.append("))");
//      }
//      selectQuery.append(") and intUser.role = ? ");
//      selectQuery.append("union ");
//      selectQuery
//          .append(
//          "select instanceId from SB_Workflow_WorkingUser wkUser where I.instanceId = wkUser.instanceId and (");
//      selectQuery.append("wkUser.userId = ? ");
//      if ((userRoles != null) && (userRoles.length > 0)) {
//        selectQuery.append(" or wkUser.usersRole in (");
//        selectQuery.append(getSQLClauseIn(userRoles));
//        selectQuery.append(")");
//      }
//      if (userGroupIds != null && userGroupIds.length > 0) {
//        selectQuery.append(" or (wkUser.groupId is not null");
//        selectQuery.append(" and wkUser.groupId in (");
//        selectQuery.append(getSQLClauseIn(userGroupIds));
//        selectQuery.append("))");
//      }
//      selectQuery.append(") and ");
//
//      // role can be multiple (e.g: "role1,role2,...,roleN")
//      selectQuery.append("( wkUser.role = ? ");
//      selectQuery.append(" or wkUser.role like ? ");
//      selectQuery.append(" or wkUser.role like ? ");
//      selectQuery.append(" or wkUser.role like ? ");
//      selectQuery.append(")");
//
//      selectQuery.append(")");
//      selectQuery.append("order by I.instanceId desc");
//
//      SilverTrace.info("worflowEngine", "ProcessInstanceManagerImpl.getProcessInstances()",
//          "root.MSG_GEN_PARAM_VALUE", "SQL query = " + selectQuery.toString());
//
//      prepStmt = con.prepareStatement(selectQuery.toString());
//      prepStmt.setString(1, peasId);
//      prepStmt.setString(2, user.getUserId());
//      prepStmt.setString(3, role);
//      prepStmt.setString(4, user.getUserId());
//      prepStmt.setString(5, role);
//      prepStmt.setString(6, "%," + role);
//      prepStmt.setString(7, role + ",%");
//      prepStmt.setString(8, "%," + role + ",%");
//    }
    
  }

  @Override
  public List<ProcessInstanceMdl> getProcessInstancesInState(String peasId, State state)
      throws WorkflowException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProcessInstanceMdl getProcessInstance(String instanceId) throws WorkflowException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HistoryStep createHistoryStep() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Actor createActor(User user, String roleName, State state) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ProcessInstanceMdl> getTimeOutProcessInstances() throws WorkflowException {
    // TODO Auto-generated method stub
    return null;
  }

  
  
}
