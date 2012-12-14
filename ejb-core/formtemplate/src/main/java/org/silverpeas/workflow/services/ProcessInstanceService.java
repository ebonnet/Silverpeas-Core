package org.silverpeas.workflow.services;

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
  
  public List<ProcessInstanceMdl> getListProcessInstance(String userId) {
    return processInstance.findProcessInstanceByUserId(userId);
  }
}
