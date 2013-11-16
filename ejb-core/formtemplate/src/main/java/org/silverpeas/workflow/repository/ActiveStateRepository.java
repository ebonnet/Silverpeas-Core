package org.silverpeas.workflow.repository;

import org.silverpeas.workflow.model.ActiveStateMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ActiveStateRepository extends JpaRepository<ActiveStateMdl, Integer> {

  @Query(value = "from ActiveStateMdl state WHERE state.processInstanceMdl.instanceId = :instanceId")
  public ActiveStateMdl getActiveState(@Param("instanceId") Integer instanceId);

}
