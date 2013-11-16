package org.silverpeas.workflow.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.silverpeas.workflow.model.ActiveStateMdl;
import org.silverpeas.workflow.repository.ActiveStateRepository;
import org.springframework.transaction.annotation.Transactional;

import com.silverpeas.annotation.Service;


@Named
@Service
@Transactional
public class ActiveStateService {

  @Inject
  private ActiveStateRepository activeStateRepo;
  
  public ActiveStateMdl getActiveState(Integer instanceId) {
    return activeStateRepo.getActiveState(instanceId);
  }
  
  public void updateActiveState(ActiveStateMdl asToUpdate) {
    activeStateRepo.saveAndFlush(asToUpdate);
  }
  
  public void deleteActiveState(ActiveStateMdl asToDelete) {
    activeStateRepo.delete(asToDelete);
  }
}
