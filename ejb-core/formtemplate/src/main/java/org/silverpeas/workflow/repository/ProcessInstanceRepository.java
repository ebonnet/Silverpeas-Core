package org.silverpeas.workflow.repository;

import java.util.List;

import org.silverpeas.workflow.model.ProcessInstanceMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProcessInstanceRepository extends JpaRepository<ProcessInstanceMdl, Integer> {

  @Query(value = "SELECT process FROM ProcessInstanceMdl process, InterestedUserMdl iUser, WorkingUserMdl wUser "
      + "WHERE process.instanceId = iUser.id AND iUser.userId = :userId "
      + "AND process.instanceId = wUser.id AND wUser.userId = :userId")
  public List<ProcessInstanceMdl> findProcessInstanceByUserId(@Param("userId") String userId);

  @Query(value = "SELECT DISTINCT process FROM ProcessInstanceMdl process LEFT JOIN process.interestedUsers iUsers LEFT JOIN process.workingUsers wUsers "
      + "WHERE process.applicationId = :applicationId" 
      + " AND (iUsers.userId = :userId OR iUsers.usersRole IN (:usersRole) "
      + " OR wUsers.userId = :userId OR wUsers.usersRole IN (:usersRole) )")
  public List<ProcessInstanceMdl> findUserProcessInstance(
      @Param("applicationId") String applicationId, @Param("userId") String userId, @Param("usersRole") List<String> usersRole);

  @Query(value = "SELECT process FROM ProcessInstanceMdl process WHERE process.applicationId = :applicationId")
  public List<ProcessInstanceMdl> findSupervisorProcessInstance(
      @Param("applicationId") String applicationId);
}
