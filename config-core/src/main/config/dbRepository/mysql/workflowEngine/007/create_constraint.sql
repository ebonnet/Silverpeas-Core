alter table sb_workflow_activestate add constraint pk_workflow_activestate primary key ( id );

alter table sb_workflow_historystep add 
	constraint pk_workflow_historystep primary key ( id ) ;

alter table sb_workflow_interesteduser add 
	constraint pk_workflow_interesteduser primary key ( id ) ;

alter table sb_workflow_lockinguser add 
	constraint pk_workflow_lockinguser primary key ( id );

alter table sb_workflow_processinstance add 
	constraint pk_workflow_processinstance primary key ( instanceid );

alter table sb_workflow_undo_step add 
	constraint pk_workflow_undo_step primary key ( id );

alter table sb_workflow_workinguser add 
	constraint pk_workflow_workinguser primary key ( id );

alter table sb_workflow_question add 
	constraint pk_workflow_question primary key ( id );

alter table sb_workflow_userinfo add 
	constraint pk_workflow_userinfo primary key ( id );

alter table sb_workflow_usersettings add 
	constraint pk_workflow_usersettings primary key ( settingsid );

alter table sb_workflow_error add 
	constraint pk_workflow_error primary key (id);

alter table sb_workflow_activestate add 
	constraint fk_workflow_activestate foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_historystep add 
	constraint fk_workflow_historystep foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_interesteduser add 
	constraint fk_workflow_interesteduser foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_lockinguser add 
	constraint fk_workflow_lockinguser foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_workinguser add 
	constraint fk_workflow_workinguser foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_question add 
	constraint fk_workflow_question foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_userinfo add 
	constraint fk_workflow_userinfo foreign key ( settingsid ) references sb_workflow_usersettings (settingsid);


