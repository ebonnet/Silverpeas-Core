package org.silverpeas.workflow.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * <query>insert into uniqueid select max(id) as maxid,
 * 'sb_workflow_activestate' as tablename from sb_workflow_activestate</query>
 * @author ebonnet
 *
 */

@Entity
@Table(name = "sb_workflow_activestate")
public class ActiveStateMdl {
  @Id
  @TableGenerator(name = "UNIQUE_ID_GEN", table = "uniqueId", pkColumnName = "tablename",
  valueColumnName = "maxId", pkColumnValue = "sb_workflow_activestate", allocationSize=1)
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "UNIQUE_ID_GEN")
  private Integer id;

  // Main relation ActiveStateMdl (one) -> ProcessInstanceMdl (one)
  // Implemented by a foreign key (instanceid) inside ActiveStateMdl
//    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinColumn(name = "adresse_id", unique = true, nullable = false)
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "instanceid", unique=true, nullable = false) //, updatable = true
  private ProcessInstanceMdl processInstanceMdl;

  @Column
  private String state;
  @Column
  private int backStatus;
  @Column
  private int timeoutStatus;
  @Column
  private Date timeoutDate;

  /**
   * Default Constructor
   */
  public ActiveStateMdl() {
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
   * @return the backStatus
   */
  public int getBackStatus() {
    return backStatus;
  }

  /**
   * @param backStatus the backStatus to set
   */
  public void setBackStatus(int backStatus) {
    this.backStatus = backStatus;
  }

  /**
   * @return the errorStatus
   */
  public int getTimeoutStatus() {
    return timeoutStatus;
  }

  /**
   * @param errorStatus the errorStatus to set
   */
  public void setTimeoutStatus(int timeoutStatus) {
    this.timeoutStatus = timeoutStatus;
  }

  /**
   * @return the timeoutDate
   */
  public Date getTimeoutDate() {
    return timeoutDate;
  }

  /**
   * @param timeoutDate the timeoutDate to set
   */
  public void setTimeoutDate(Date timeoutDate) {
    this.timeoutDate = timeoutDate;
  }

  
  
}
