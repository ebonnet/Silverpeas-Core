package org.silverpeas.workflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Be careful to initialize the database with the following query in order to get the generated
 * value working <query>insert into uniqueid select max(instanceid) as maxid,
 * 'sb_workflow_processinstance' as tablename from sb_workflow_processinstance</query>
 * @author ebonnet
 */

@Entity
@Table(name = "sb_workflow_processinstance")
public class ProcessInstanceMdl implements Serializable {

  private static final long serialVersionUID = 6984310422556506475L;

  @Id
  @TableGenerator(name = "UNIQUE_ID_GEN", table = "uniqueId", pkColumnName = "tablename",
      valueColumnName = "maxId", pkColumnValue = "sb_workflow_processinstance", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "UNIQUE_ID_GEN")
  private Integer instanceId;
  @Column(name = "modelid", length = 50, nullable = false)
  private String applicationId;
  @Column
  private int locked;
  @Column
  private int errorStatus;
  @Column
  private int timeoutStatus;
  
  // Reverse relation ProcessInstanceMdl (one) -> InterestedUsers (many) of relation InterestedUsers
  // (many) -> ProcessInstanceMdl (one)
  // cascade insert
  // cascade update ProcessInstanceMdl -> update InterestedUsers
  // cascade delete ProcessInstanceMdl -> delete InterestedUsers
  @OneToMany(mappedBy = "processInstanceMdl", cascade = { CascadeType.ALL })
  private List<InterestedUserMdl> interestedUsers = new ArrayList<InterestedUserMdl>();

  @OneToMany(mappedBy = "processInstanceMdl", cascade = { CascadeType.ALL })
  private List<WorkingUserMdl> workingUsers = new ArrayList<WorkingUserMdl>();
  
  @OneToOne(mappedBy = "processInstanceMdl", cascade= { CascadeType.ALL })
  private ActiveStateMdl activeState;

  
  
  /**
   * Default ProcessInstanceMdl constructor
   */
  public ProcessInstanceMdl() {
  }
  
  /**
   * @param instanceId
   * @param applicationId
   * @param locked
   * @param errorStatus
   * @param timeoutStatus
   * @param interestedUsers
   * @param workingUsers
   * @param activeState
   */
  public ProcessInstanceMdl(Integer instanceId, String applicationId, int locked, int errorStatus,
      int timeoutStatus, List<InterestedUserMdl> interestedUsers,
      List<WorkingUserMdl> workingUsers, ActiveStateMdl activeState) {
    super();
    this.instanceId = instanceId;
    this.applicationId = applicationId;
    this.locked = locked;
    this.errorStatus = errorStatus;
    this.timeoutStatus = timeoutStatus;
    this.interestedUsers = interestedUsers;
    this.workingUsers = workingUsers;
    this.activeState = activeState;
  }


  // association bidirectionnelle Categorie <--> Article
  public void addInterestedUser(InterestedUserMdl interestedUser) {
    interestedUsers.add(interestedUser);
    // Set current process instance model inside InterestedUser
    interestedUser.setProcessInstanceMdl(this);
  }

  
  /*
   * GETTER and SETTER 
   */
  
  /**
   * @return the interestedUsers
   */
  public List<InterestedUserMdl> getInterestedUsers() {
    return interestedUsers;
  }

  /**
   * @param interestedUsers the interestedUsers to set
   */
  public void setInterestedUsers(List<InterestedUserMdl> interestedUsers) {
    this.interestedUsers = interestedUsers;
  }

  /**
   * @return the workingUsers
   */
  public List<WorkingUserMdl> getWorkingUsers() {
    return workingUsers;
  }

  /**
   * @param workingUsers the workingUsers to set
   */
  public void setWorkingUsers(List<WorkingUserMdl> workingUsers) {
    this.workingUsers = workingUsers;
  }


  /**
   * @return the activeState
   */
  public ActiveStateMdl getActiveState() {
    return activeState;
  }


  /**
   * @param activeState the activeState to set
   */
  public void setActiveState(ActiveStateMdl activeState) {
    this.activeState = activeState;
  }

  /**
   * @return the instanceId
   */
  public Integer getInstanceId() {
    return instanceId;
  }

  /**
   * @param instanceId the instanceId to set
   */
  public void setInstanceId(Integer instanceId) {
    this.instanceId = instanceId;
  }

  /**
   * @return the applicationId
   */
  public String getApplicationId() {
    return applicationId;
  }

  /**
   * @param applicationId the applicationId to set
   */
  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  /**
   * @return the locked
   */
  public int getLocked() {
    return locked;
  }

  /**
   * @param locked the locked to set
   */
  public void setLocked(int locked) {
    this.locked = locked;
  }

  /**
   * @return the errorStatus
   */
  public int getErrorStatus() {
    return errorStatus;
  }

  /**
   * @param errorStatus the errorStatus to set
   */
  public void setErrorStatus(int errorStatus) {
    this.errorStatus = errorStatus;
  }

  /**
   * @return the timeoutStatus
   */
  public int getTimeoutStatus() {
    return timeoutStatus;
  }

  /**
   * @param timeoutStatus the timeoutStatus to set
   */
  public void setTimeoutStatus(int timeoutStatus) {
    this.timeoutStatus = timeoutStatus;
  }

  
  
}
