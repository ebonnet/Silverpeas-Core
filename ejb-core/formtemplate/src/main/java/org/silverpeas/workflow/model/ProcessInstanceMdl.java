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
  private Integer instanceid;
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

  // association bidirectionnelle Categorie <--> Article
  public void addInterestedUser(InterestedUserMdl interestedUser) {
    // l'article est ajouté dans la collection des articles de la catégorie
    interestedUsers.add(interestedUser);
    // l'article change de catégorie
    interestedUser.setProcessInstanceMdl(this);
  }

}
