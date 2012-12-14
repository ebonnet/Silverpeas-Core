CREATE TABLE uniqueid (
	maxId int NOT NULL ,
	tableName varchar(100) NOT NULL
);


CREATE TABLE sb_workflow_processinstance
(
  instanceId  int   NOT NULL ,
  modelId   varchar (50)  NOT NULL ,
  locked    int(1)    NOT NULL ,
  errorStatus int(1)    DEFAULT 0 NOT NULL,
  timeoutStatus int(1)  DEFAULT 0 NOT NULL
)
;

CREATE TABLE sb_workflow_activestate
(
  id    int NOT NULL ,
  instanceId  int NOT NULL ,
  state   varchar (50) NOT NULL ,
  backStatus  int(1) DEFAULT 0 NOT NULL,
  timeoutStatus int(1) DEFAULT 0 NOT NULL,
  timeoutDate timestamp
);

CREATE TABLE sb_workflow_historystep
(
  instanceId    int NOT NULL ,
  id            int NOT NULL ,
  userId        varchar (50) ,
  userRoleName  varchar (50) ,
  action        varchar (50) ,
  actionDate    timestamp ,
  resolvedState varchar (50) ,
  toState       varchar (50) ,
  actionStatus  int 
);

CREATE TABLE sb_workflow_undo_step
(
  id         int NOT NULL ,
  stepId     int NOT NULL ,
  instanceId int NOT NULL ,
  action     varchar (20) NOT NULL ,
  parameters varchar (150) NOT NULL 
)
;

CREATE TABLE sb_workflow_interesteduser
(
  id         int NOT NULL ,
  userId     varchar (50) NULL ,
  usersrole  varchar (50) NULL ,
  instanceId int NOT NULL ,
  state      varchar (50) NOT NULL ,
  role       varchar (50) NOT NULL,
  groupid    varchar (50) NULL
)
;

CREATE TABLE sb_workflow_lockinguser
(
  id         int NOT NULL ,
  userId     varchar (50) NOT NULL ,
  instanceId int NOT NULL ,
  state      varchar (50) NOT NULL ,
  lockDate   timestamp 
)
;

CREATE TABLE sb_workflow_workinguser
(
  id         int NOT NULL ,
  userId     varchar (50) NULL ,
  usersrole  varchar (50) NULL ,
  instanceId int NOT NULL ,
  state      varchar (50) NOT NULL ,
  role       varchar (50) NOT NULL,
  groupid    varchar (50) NULL
)
;

CREATE TABLE sb_workflow_question
(
  id    int NOT NULL ,
  instanceId  int NOT NULL ,
  questionText  varchar (500) NOT NULL ,
  responseText  varchar (500) NULL ,
  questionDate  timestamp NOT NULL ,
  responseDate  timestamp NULL ,
  fromState varchar (50) NOT NULL ,
  targetState varchar (50) NOT NULL ,
  fromUserId  varchar (50) NOT NULL ,
  toUserId  varchar (50) NOT NULL ,
  relevant  int(1) NOT NULL 
)
;

CREATE TABLE sb_workflow_userinfo
(
  id    int NOT NULL ,
  settingsId  int NOT NULL ,
  name    varchar (50) NOT NULL ,
  value   varchar (100) NULL 
)
;

CREATE TABLE sb_workflow_usersettings
(
  settingsId  int NOT NULL ,
  userId    varchar (100) NOT NULL ,
  peasId    varchar (100) NOT NULL 
)
;

CREATE TABLE sb_workflow_error
(
  id    int   not null,
  instanceId  int   not null,
  stepId    int   null,
  errorMessage  varchar (200) null,
  stackTrace  varchar (4000)  null,
  userId    varchar (100) null,
  actionName  varchar (100) null,
  actionDate  timestamp null,
  userRole  varchar (100) null,
  stateName varchar (100) null
)
;


alter table sb_workflow_activestate add constraint pk_workflow_activestate primary key ( id );

alter table sb_workflow_historystep add constraint pk_workflow_historystep primary key ( id );

alter table sb_workflow_interesteduser add constraint pk_workflow_interesteduser primary key ( id );

alter table sb_workflow_lockinguser add constraint pk_workflow_lockinguser primary key ( id );

alter table sb_workflow_processinstance add constraint pk_workflow_processinstance primary key ( instanceid );

alter table sb_workflow_undo_step add constraint pk_workflow_undo_step primary key ( id );

alter table sb_workflow_workinguser add constraint pk_workflow_workinguser primary key ( id );

alter table sb_workflow_question add constraint pk_workflow_question primary key ( id );

alter table sb_workflow_userinfo add constraint pk_workflow_userinfo primary key ( id );

alter table sb_workflow_usersettings add constraint pk_workflow_usersettings primary key ( settingsid );

alter table sb_workflow_error add constraint pk_workflow_error primary key (id);

alter table sb_workflow_activestate add constraint fk_workflow_activestate
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_historystep add constraint fk_workflow_historystep
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_interesteduser add constraint fk_workflow_interesteduser
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_lockinguser add constraint fk_workflow_lockinguser
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_workinguser add constraint fk_workflow_workinguser
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_question add constraint fk_workflow_question
  foreign key ( instanceid ) references sb_workflow_processinstance ( instanceid );

alter table sb_workflow_userinfo add  constraint fk_workflow_userinfo
  foreign key ( settingsid ) references sb_workflow_usersettings ( settingsid );

