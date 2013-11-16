package org.silverpeas.workflow.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.silverpeas.workflow.model.InterestedUserMdl;
import org.silverpeas.workflow.repository.InterestedUserRepository;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;


@Named
@Service
@Transactional
public class InterestedUserService {

  @Inject
  private InterestedUserRepository interestedUserRepo;
  
  public Integer createInterestedUser(InterestedUserMdl iuToCreate) {
    InterestedUserMdl savedInterestedUser = interestedUserRepo.saveAndFlush(iuToCreate);
    return savedInterestedUser.getId();
  }
  
  public List<InterestedUserMdl> getListInterestedUser(Integer instanceId) {
    return interestedUserRepo.getInterestedUserByInstanceId(instanceId);
  }
}
