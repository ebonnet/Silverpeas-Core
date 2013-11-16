package org.silverpeas.workflow.repository;

import java.util.List;

import org.silverpeas.workflow.model.WorkingUserMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface WorkingUserRepository extends JpaRepository<WorkingUserMdl, Integer> {

  @Query(value = "from WorkingUserMdl wUser WHERE wUser.processInstanceMdl.instanceId = :instanceId ")
  public List<WorkingUserMdl> getWorkingUserByInstanceId(@Param("instanceId") String instanceId);

}
