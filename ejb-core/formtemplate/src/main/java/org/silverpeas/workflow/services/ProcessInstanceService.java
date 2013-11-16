package org.silverpeas.workflow.services;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.silverpeas.workflow.repository.ProcessInstanceRepository;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;

@Named
@Service
@Transactional
public class ProcessInstanceService {

  @Inject
  private ProcessInstanceRepository processInstance;

  public List<ProcessInstanceMdl> getListSupervisorProcessInstance(String applicationId) {
    return processInstance.findSupervisorProcessInstance(applicationId);
  }

  public List<ProcessInstanceMdl> getListUserProcessInstance(String applicationId, String userId,
      String role, String[] userRoles, String[] userGroupIds) {
    return processInstance.findUserProcessInstance(applicationId, userId, Arrays.asList(userRoles));
  }

  public List<ProcessInstanceMdl> getListProcessInstance(String applicationId, String userId) {
    return processInstance.findProcessInstanceByUserId(userId);
  }

  public void deleteProcessInstance(ProcessInstanceMdl pimToDelete) {
    processInstance.delete(pimToDelete);
  }

  public ProcessInstanceMdl getProcessInstance(Integer processInstanceId) {
    return processInstance.findOne(processInstanceId);
  }

}
