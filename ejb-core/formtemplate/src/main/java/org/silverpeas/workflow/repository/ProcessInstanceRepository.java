package org.silverpeas.workflow.repository;

import java.util.List;

import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProcessInstanceRepository extends JpaRepository<ProcessInstanceMdl, Integer> {

  @Query(value = "from ProcessInstanceMdl process, InterestedUserMdl iUser WHERE process.instanceId = iUser.instanceId and iUser = :userId")
  public List<ProcessInstanceMdl> findProcessInstanceByUserId(@Param("userId") String userId);

}
