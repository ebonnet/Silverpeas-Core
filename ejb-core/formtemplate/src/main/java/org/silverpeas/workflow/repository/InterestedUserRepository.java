package org.silverpeas.workflow.repository;

import java.util.List;

import org.silverpeas.workflow.model.InterestedUserMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InterestedUserRepository extends JpaRepository<InterestedUserMdl, Integer> {

  @Query(value = "from InterestedUserMdl iUser WHERE iUser.processInstanceMdl.instanceId = :instanceId ")
  public List<InterestedUserMdl> getInterestedUserByInstanceId(@Param("instanceId") Integer instanceId);

}
