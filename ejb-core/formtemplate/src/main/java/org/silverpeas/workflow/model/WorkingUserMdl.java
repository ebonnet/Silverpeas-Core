package org.silverpeas.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * <query>insert into uniqueid select max(id) as maxid,
 * 'sb_workflow_workinguser' as tablename from sb_workflow_workinguser</query>
 * @author ebonnet
 *
 */

@Entity
@Table(name = "sb_workflow_workinguser")
public class WorkingUserMdl {
  @Id
  @TableGenerator(name = "UNIQUE_ID_GEN", table = "uniqueId", pkColumnName = "tablename",
  valueColumnName = "maxId", pkColumnValue = "sb_workflow_workinguser", allocationSize=1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "UNIQUE_ID_GEN")
  private Integer id;
  
  @Column
  private String userId;
  
  // @See InterestedUserMdl relation
  @ManyToOne(optional = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "instanceid", nullable = true, updatable = true)
  private ProcessInstanceMdl processInstanceMdl;
  
  @Column
  private String state;
  @Column
  private String role;
  @Column
  private String usersRole;
  @Column
  private String groupId;
 
  /**
   * Default Constructor
   */
  public WorkingUserMdl() {
    super();
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return the processInstanceMdl
   */
  public ProcessInstanceMdl getProcessInstanceMdl() {
    return processInstanceMdl;
  }

  /**
   * @param processInstanceMdl the processInstanceMdl to set
   */
  public void setProcessInstanceMdl(ProcessInstanceMdl processInstanceMdl) {
    this.processInstanceMdl = processInstanceMdl;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * @return the usersRole
   */
  public String getUsersRole() {
    return usersRole;
  }

  /**
   * @param usersRole the usersRole to set
   */
  public void setUsersRole(String usersRole) {
    this.usersRole = usersRole;
  }

  /**
   * @return the groupId
   */
  public String getGroupId() {
    return groupId;
  }

  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  
  
}
