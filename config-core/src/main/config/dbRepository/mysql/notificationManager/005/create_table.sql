CREATE TABLE st_notifchannel (
    id int(11) NOT NULL,
    name varchar(20) NOT NULL,
    description varchar(200),
    couldbeadded char(1) DEFAULT 'Y' NOT NULL,
    fromavailable char(1) DEFAULT 'N' NOT NULL,
    subjectavailable char(1) DEFAULT 'N' NOT NULL
) ENGINE=InnoDB;


CREATE TABLE st_notifaddress (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    notifname varchar(20) NOT NULL,
    notifchannelid int(11) NOT NULL,
    address varchar(250) NOT NULL,
    usage1 varchar(20),
    priority int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_notifdefaultaddress (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    notifaddressid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_notifpreference (
    id int(11) NOT NULL,
    notifaddressid int(11) NOT NULL,
    componentinstanceid int(11) NOT NULL,
    userid int(11) NOT NULL,
    messagetype int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_notifsended (
    notifid int(11) NOT NULL,
    userid int(11) NOT NULL,
    messagetype int(11),
    notifdate char(13) NOT NULL,
    title varchar(255),
    link varchar(255),
    sessionid varchar(255),
    componentid varchar(255),
    body int(11)
) ENGINE=InnoDB;

CREATE TABLE st_notifsendedreceiver (
    notifid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;


CREATE TABLE st_delayednotifusersetting (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    channel int(11) NOT NULL,
    frequency varchar(4) NOT NULL
) ENGINE=InnoDB;


CREATE TABLE st_notificationresource (
    id bigint NOT NULL,
    componentinstanceid varchar(50) NOT NULL,
    resourceid varchar(50) NOT NULL,
    resourcetype varchar(50) NOT NULL,
    resourcename varchar(500) NOT NULL,
    resourcedescription varchar(2000),
    resourcelocation varchar(500) NOT NULL,
    resourceurl varchar(1000)
) ENGINE=InnoDB;

CREATE TABLE st_delayednotification (
    id bigint NOT NULL,
    userid int(11) NOT NULL,
    fromuserid int(11) NOT NULL,
    channel int(11) NOT NULL,
    action int(11) NOT NULL,
    notificationresourceid bigint NOT NULL,
    language varchar(2) NOT NULL,
    creationdate timestamp NOT NULL,
    message varchar(2000)
) ENGINE=InnoDB;
