CREATE TABLE sb_workflow_processinstance (
    instanceid int(11) NOT NULL,
    modelid varchar(50) NOT NULL,
    locked tinyint NOT NULL,
    errorstatus tinyint DEFAULT 0 NOT NULL,
    timeoutstatus tinyint DEFAULT 0 NOT NULL
) ENGINE=InnoDB;


CREATE TABLE sb_workflow_activestate (
    id int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    state varchar(50) NOT NULL,
    backstatus tinyint DEFAULT 0 NOT NULL,
    timeoutstatus tinyint DEFAULT 0 NOT NULL,
    timeoutdate timestamp
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_historystep (
    instanceid int(11) NOT NULL,
    id int(11) NOT NULL,
    userid varchar(50),
    userrolename varchar(50),
    action varchar(50),
    actiondate timestamp,
    resolvedstate varchar(50),
    tostate varchar(50),
    actionstatus int(11)
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_undo_step (
    id int(11) NOT NULL,
    stepid int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    action varchar(20) NOT NULL,
    parameters varchar(150) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_interesteduser (
    id int(11) NOT NULL,
    userid varchar(50),
    instanceid int(11) NOT NULL,
    state varchar(50) NOT NULL,
    role varchar(50) NOT NULL,
    usersrole varchar(50),
    groupid varchar(50)
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_lockinguser (
    id int(11) NOT NULL,
    userid varchar(50) NOT NULL,
    instanceid int(11) NOT NULL,
    state varchar(50) NOT NULL,
    lockdate timestamp
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_workinguser (
    id int(11) NOT NULL,
    userid varchar(50),
    instanceid int(11) NOT NULL,
    state varchar(50) NOT NULL,
    role varchar(50) NOT NULL,
    usersrole varchar(50),
    groupid varchar(50)
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_question (
    id int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    questiontext varchar(500) NOT NULL,
    responsetext varchar(500),
    questiondate timestamp NOT NULL,
    responsedate timestamp,
    fromstate varchar(50) NOT NULL,
    targetstate varchar(50) NOT NULL,
    fromuserid varchar(50) NOT NULL,
    touserid varchar(50) NOT NULL,
    relevant tinyint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_userinfo (
    id int(11) NOT NULL,
    settingsid int(11) NOT NULL,
    name varchar(50) NOT NULL,
    value varchar(100)
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_usersettings (
    settingsid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    peasid varchar(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_workflow_error (
    id int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    stepid int(11),
    errormessage varchar(200),
    stacktrace varchar(4000),
    userid varchar(100),
    actionname varchar(100),
    actiondate timestamp,
    userrole varchar(100),
    statename varchar(100)
) ENGINE=InnoDB;
