alter table sb_workflow_activestate drop foreign key fk_workflow_activestate;
alter table sb_workflow_historystep drop foreign key fk_workflow_historystep;
alter table sb_workflow_interesteduser drop foreign key fk_workflow_interesteduser;
alter table sb_workflow_lockinguser drop foreign key fk_workflow_lockinguser;
alter table sb_workflow_workinguser drop foreign key fk_workflow_workinguser;
alter table sb_workflow_question drop foreign key fk_workflow_question;
alter table sb_workflow_userinfo drop foreign key fk_workflow_userinfo;

alter table sb_workflow_question drop primary key;
alter table sb_workflow_userinfo drop primary key;
alter table sb_workflow_usersettings drop primary key;
alter table sb_workflow_activestate drop primary key;
alter table sb_workflow_historystep drop primary key;
alter table sb_workflow_interesteduser drop primary key;
alter table sb_workflow_lockinguser drop primary key;
alter table sb_workflow_processinstance drop primary key;
alter table sb_workflow_undo_step drop primary key;
alter table sb_workflow_workinguser drop primary key;
alter table sb_workflow_error drop primary key;
