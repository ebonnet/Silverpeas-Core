CREATE TABLE sb_publication_info (
    infoid int(11) NOT NULL,
    modelid int(11),
    infocontent varchar(2000),
    instanceid varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_infoattachment (
    infoattachmentid int(11) NOT NULL,
    infoid int(11) NOT NULL,
    infoattachmentphysicalname varchar(1000) NOT NULL,
    infoattachmentlogicalname varchar(1000) NOT NULL,
    infoattachmentdescription varchar(2000),
    infoattachmenttype varchar(50) NOT NULL,
    infoattachmentsize int(11),
    infoattachmentdisplayorder int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_infoimage (
    infoimageid int(11) NOT NULL,
    infoid int(11) NOT NULL,
    infoimagephysicalname varchar(1000) NOT NULL,
    infoimagelogicalname varchar(1000) NOT NULL,
    infoimagedescription varchar(2000),
    infoimagetype varchar(50) NOT NULL,
    infoimagesize int(11),
    infoimagedisplayorder int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_infolink (
    infolinkid int(11) NOT NULL,
    infoid int(11) NOT NULL,
    pubid int(11) NOT NULL,
    infolinkdisplayorder int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_infotext (
    infotextid int(11) NOT NULL,
    infoid int(11) NOT NULL,
    infotextcontent varchar(4000),
    infotextdisplayorder int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_publi (
    pubid int(11) NOT NULL,
    infoid varchar(50),
    pubname varchar(400) NOT NULL,
    pubdescription varchar(2000),
    pubcreationdate varchar(10) NOT NULL,
    pubbegindate varchar(10) NOT NULL,
    pubenddate varchar(10) NOT NULL,
    pubcreatorid varchar(100) NOT NULL,
    pubimportance int(11),
    pubversion varchar(100),
    pubkeywords varchar(1000),
    pubcontent varchar(2000),
    pubstatus varchar(100),
    pubupdatedate varchar(10),
    instanceid varchar(50) NOT NULL,
    pubupdaterid varchar(100),
    pubvalidatedate varchar(10),
    pubvalidatorid varchar(50),
    pubbeginhour varchar(5),
    pubendhour varchar(5),
    pubauthor varchar(50),
    pubtargetvalidatorid varchar(50),
    pubcloneid int(11) DEFAULT -1 ,
    pubclonestatus varchar(50),
    lang char(2),
    pubdraftoutdate varchar(10)
) ENGINE=InnoDB;

CREATE TABLE sb_publication_publifather (
    pubid int(11) NOT NULL,
    nodeid int(11) NOT NULL,
    instanceid varchar(50) NOT NULL,
    aliasuserid int(11),
    aliasdate varchar(20),
    puborder int(11) DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE sb_seealso_link (
    id int(11) NOT NULL,
    objectid int(11) NOT NULL,
    objectinstanceid varchar(50) NOT NULL,
    targetid int(11) NOT NULL,
    targetinstanceid varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_publication_publii18n (
    id int(11) NOT NULL,
    pubid int(11) NOT NULL,
    lang char(2) NOT NULL,
    name varchar(400) NOT NULL,
    description varchar(2000),
    keywords varchar(1000)
) ENGINE=InnoDB;

CREATE TABLE sb_publication_validation (
    id int(11) NOT NULL,
    pubid int(11) NOT NULL,
    instanceid varchar(50) NOT NULL,
    userid int(11) NOT NULL,
    decisiondate varchar(20) NOT NULL,
    decision varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_thumbnail_thumbnail (
    instanceid varchar(50) NOT NULL,
    objectid int(11) NOT NULL,
    objecttype int(11) NOT NULL,
    originalattachmentname varchar(250) NOT NULL,
    modifiedattachmentname varchar(250),
    mimetype varchar(250),
    xstart int(11),
    ystart int(11),
    xlength int(11),
    ylength int(11)
) ENGINE=InnoDB;
