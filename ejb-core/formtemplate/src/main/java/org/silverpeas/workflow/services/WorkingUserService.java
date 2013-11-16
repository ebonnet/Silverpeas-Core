package org.silverpeas.workflow.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.silverpeas.workflow.model.WorkingUserMdl;
import org.silverpeas.workflow.repository.WorkingUserRepository;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;


@Named
@Service
@Transactional
public class WorkingUserService {

  @Inject
  private WorkingUserRepository workingUserRepo;
  
  public List<WorkingUserMdl> getListWorkingUser(String instanceId) {
    return workingUserRepo.getWorkingUserByInstanceId(instanceId);
  }
}
