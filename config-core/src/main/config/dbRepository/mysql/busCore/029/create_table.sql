CREATE TABLE st_accesslevel (
    id char(1) NOT NULL,
    name varchar(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_user (
    id int(11) NOT NULL,
    domainid int(11) NOT NULL,
    specificid varchar(500) NOT NULL,
    firstname varchar(100),
    lastname varchar(100) NOT NULL,
    email varchar(100),
    login varchar(50) NOT NULL,
    loginmail varchar(100),
    accesslevel char(1) DEFAULT 'U' NOT NULL,
    loginquestion varchar(200),
    loginanswer varchar(200)
) ENGINE=InnoDB;

CREATE TABLE st_group (
    id int(11) NOT NULL,
    domainid int(11) NOT NULL,
    specificid varchar(500) NOT NULL,
    supergroupid int(11),
    name varchar(100) NOT NULL,
    description varchar(400),
    synchrorule varchar(100)
) ENGINE=InnoDB;


CREATE TABLE st_group_user_rel (
    groupid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_space (
    id int(11) NOT NULL,
    domainfatherid int(11),
    name varchar(100) NOT NULL,
    description varchar(400),
    createdby int(11),
    firstpagetype int(11) NOT NULL,
    firstpageextraparam varchar(400),
    ordernum int(11) DEFAULT 0 NOT NULL,
    createtime varchar(20),
    updatetime varchar(20),
    removetime varchar(20),
    spacestatus char(1),
    updatedby int(11),
    removedby int(11),
    lang char(2),
    isinheritanceblocked int(11) DEFAULT 0 NOT NULL,
    look varchar(50),
    displayspacefirst smallint,
    ispersonal smallint
) ENGINE=InnoDB;

CREATE TABLE st_spacei18n (
    id int(11) NOT NULL,
    spaceid int(11) NOT NULL,
    lang char(2) NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(400)
) ENGINE=InnoDB;


CREATE TABLE st_componentinstance (
    id int(11) NOT NULL,
    spaceid int(11) NOT NULL,
    name varchar(100) NOT NULL,
    componentname varchar(100) NOT NULL,
    description varchar(400),
    createdby int(11),
    ordernum int(11) DEFAULT 0 NOT NULL,
    createtime varchar(20),
    updatetime varchar(20),
    removetime varchar(20),
    componentstatus char(1),
    updatedby int(11),
    removedby int(11),
    ispublic int(11) DEFAULT 0 NOT NULL,
    ishidden int(11) DEFAULT 0 NOT NULL,
    lang char(2),
    isinheritanceblocked int(11) DEFAULT 0 NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_componentinstancei18n (
    id int(11) NOT NULL,
    componentid int(11) NOT NULL,
    lang char(2) NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(400)
) ENGINE=InnoDB;

CREATE TABLE st_instance_data (
    id int(11) NOT NULL,
    componentid int(11) NOT NULL,
    name varchar(100) NOT NULL,
    label varchar(100) NOT NULL,
    value varchar(400)
) ENGINE=InnoDB;

CREATE TABLE st_userrole (
    id int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    name varchar(100),
    rolename varchar(100) NOT NULL,
    description varchar(400),
    isinherited int(11) DEFAULT 0 NOT NULL,
    objectid int(11),
    objecttype varchar(50)
) ENGINE=InnoDB;


CREATE TABLE st_userrole_group_rel (
    userroleid int(11) NOT NULL,
    groupid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_userrole_user_rel (
    userroleid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_spaceuserrole (
    id int(11) NOT NULL,
    spaceid int(11) NOT NULL,
    name varchar(100),
    rolename varchar(100) NOT NULL,
    description varchar(400),
    isinherited int(11) DEFAULT 0 NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_spaceuserrole_group_rel (
    spaceuserroleid int(11) NOT NULL,
    groupid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_spaceuserrole_user_rel (
    spaceuserroleid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE domainsp_group (
    id int(11) NOT NULL,
    supergroupid int(11),
    name varchar(100) NOT NULL,
    description varchar(400)
) ENGINE=InnoDB;

CREATE TABLE domainsp_user (
    id int(11) NOT NULL,
    firstname varchar(100),
    lastname varchar(100) NOT NULL,
    phone varchar(20),
    homephone varchar(20),
    cellphone varchar(20),
    fax varchar(20),
    address varchar(500),
    title varchar(100),
    company varchar(100),
    `position` varchar(100),
    boss varchar(100),
    login varchar(50) NOT NULL,
    password varchar(32),
    passwordvalid char(1) DEFAULT 'Y' NOT NULL,
    loginmail varchar(100),
    email varchar(100)
) ENGINE=InnoDB;

CREATE TABLE domainsp_group_user_rel (
    groupid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_domain (
    id int(11) NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(400),
    propfilename varchar(100) NOT NULL,
    classname varchar(100) NOT NULL,
    authenticationserver varchar(100) NOT NULL,
    thetimestamp varchar(100) DEFAULT '0' NOT NULL,
    silverpeasserverurl varchar(400)
) ENGINE=InnoDB;

CREATE TABLE st_keystore (
    userkey bigint NOT NULL,
    login varchar(50) NOT NULL,
    domainid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_longtext (
    id int(11) NOT NULL,
    ordernum int(11) NOT NULL,
    bodycontent varchar(2000) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_groupuserrole (
    id int(11) NOT NULL,
    groupid int(11) NOT NULL,
    rolename varchar(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_groupuserrole_group_rel (
    groupuserroleid int(11) NOT NULL,
    groupid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_groupuserrole_user_rel (
    groupuserroleid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_instance_modelused (
    instanceid varchar(50) NOT NULL,
    modelid varchar(50) NOT NULL,
    objectid varchar(50) DEFAULT '0' NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_userfavoritespaces (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    spaceid int(11) NOT NULL
) ENGINE=InnoDB;
