CREATE TABLE uniqueid (
    maxid bigint NOT NULL,
    tablename varchar(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE personalization (
    id varchar(100) NOT NULL,
    languages varchar(100),
    look varchar(50),
    personalwspace varchar(50),
    thesaurusstatus int(11) NOT NULL,
    draganddropstatus int(11) DEFAULT 1,
    webdaveditingstatus int(11) DEFAULT 0,
    menudisplay varchar(50) DEFAULT 'DEFAULT'
) ENGINE=InnoDB;

CREATE TABLE readingcontrol (
    pubid int(11) NOT NULL,
    actorid varchar(100) NOT NULL,
    space varchar(50) NOT NULL,
    componentname varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE subscribe (
    actorid varchar(100) NOT NULL,
    nodeid int(11) NOT NULL,
    space varchar(50) NOT NULL,
    componentname varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE model_contact (
    id int(11) NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(50),
    imagename varchar(50),
    htmldisplayer varchar(3000) NOT NULL,
    htmleditor varchar(3000) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE model (
    id int(11) NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(100),
    imagename varchar(100),
    htmldisplayer varchar(3500) NOT NULL,
    htmleditor varchar(3500) NOT NULL,
    partid int(11) DEFAULT 1 NOT NULL
) ENGINE=InnoDB;

CREATE TABLE favorit (
    actorid varchar(100) NOT NULL,
    nodeid int(11) NOT NULL,
    space varchar(50) NOT NULL,
    componentname varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE calendarjournal (
    id int(11) NOT NULL,
    name varchar(2000) NOT NULL,
    description varchar(4000),
    delegatorid varchar(100) NOT NULL,
    startday varchar(50) NOT NULL,
    endday varchar(50),
    starthour varchar(50),
    endhour varchar(50),
    classification varchar(20),
    priority int(11),
    lastmodification varchar(50),
    externalid varchar(50)
) ENGINE=InnoDB;


CREATE TABLE calendarjournalattendee (
    journalid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    participationstatus varchar(50)
) ENGINE=InnoDB;

CREATE TABLE calendarjournalcategory (
    journalid int(11) NOT NULL,
    categoryid varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE calendartodo (
    id int(11) NOT NULL,
    name varchar(2000) NOT NULL,
    description varchar(4000),
    delegatorid varchar(100) NOT NULL,
    startday varchar(50),
    endday varchar(50),
    starthour varchar(50),
    endhour varchar(50),
    classification varchar(20),
    priority int(11),
    lastmodification varchar(50),
    percentcompleted int(11),
    completedday varchar(20),
    duration int(11),
    componentid varchar(100),
    spaceid varchar(100),
    externalid varchar(100)
) ENGINE=InnoDB;

CREATE TABLE calendartodoattendee (
    todoid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    participationstatus varchar(50)
) ENGINE=InnoDB;

CREATE TABLE calendarcategory (
    categoryid varchar(50) NOT NULL,
    name varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE days (
    day varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_formdesigner_formdesign (
    id int(11) NOT NULL,
    refidform int(11) NOT NULL,
    componentid varchar(100) NOT NULL,
    name varchar(1000) NOT NULL,
    description varchar(2000) NOT NULL,
    creationdate varchar(10) NOT NULL,
    author int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_formdesigner_connectors (
    id int(11) NOT NULL,
    name varchar(1000) NOT NULL,
    description varchar(2000) NOT NULL,
    driver varchar(1000) NOT NULL,
    url varchar(1000) NOT NULL,
    login varchar(1000) NOT NULL,
    passwd varchar(1000),
    sqlquery varchar(4000) NOT NULL,
    `type` varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_formeditor_formedited (
    id int(11) NOT NULL,
    formid int(11) NOT NULL,
    userid int(11) NOT NULL,
    createdate varchar(10) NOT NULL,
    modifydate varchar(10) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_formeditor_formediteddata (
    id int(11) NOT NULL,
    formeditedid int(11) NOT NULL,
    editedkey varchar(50) NOT NULL,
    editedvalue varchar(500) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_agenda_import_settings (
    userid int(11) NOT NULL,
    hostname varchar(500) NOT NULL,
    synchrotype int(11) NOT NULL,
    synchrodelay int(11) NOT NULL,
    url varchar(500),
    remotelogin varchar(200),
    remotepwd varchar(200),
    charset varchar(20)
) ENGINE=InnoDB;

