CREATE TABLE sb_version_document (
    documentid int(11) NOT NULL,
    documentname varchar(255) NOT NULL,
    documentdescription varchar(255),
    documentstatus int(11) NOT NULL,
    documentownerid int(11),
    documentcheckoutdate char(10),
    documentinfo varchar(100),
    foreignid int(11) NOT NULL,
    instanceid varchar(50) NOT NULL,
    typeworklist int(11) NOT NULL,
    currentworklistorder int(11),
    alertdate varchar(10),
    expirydate varchar(10),
    documentordernum int(11) DEFAULT 0 NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_version_version (
    versionid int(11) NOT NULL,
    documentid int(11) NOT NULL,
    versionmajornumber int(11) NOT NULL,
    versionminornumber int(11) NOT NULL,
    versionauthorid int(11) NOT NULL,
    versioncreationdate char(10) NOT NULL,
    versioncomments varchar(1000),
    versiontype int(11) NOT NULL,
    versionstatus int(11),
    versionphysicalname varchar(100) NOT NULL,
    versionlogicalname varchar(255) NOT NULL,
    versionmimetype varchar(100) NOT NULL,
    versionsize int(11) NOT NULL,
    instanceid varchar(50) NOT NULL,
    xmlform varchar(50)
) ENGINE=InnoDB;


CREATE TABLE sb_document_worklist (
    documentid int(11) NOT NULL,
    userid int(11) NOT NULL,
    orderby int(11) NOT NULL,
    writer varchar(100),
    approval varchar(100),
    instanceid varchar(50) NOT NULL,
    settype char(1) DEFAULT 'U',
    saved int(11) DEFAULT 0 NOT NULL,
    used int(11) DEFAULT 1 NOT NULL,
    listtype int(11) DEFAULT 0 NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_doc_readers_acl (
    id int(11) NOT NULL,
    componentid varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_doc_readers_acl_list (
    id int(11) NOT NULL,
    settype char(1) NOT NULL,
    settypeid int(11) NOT NULL,
    accessid int(11) NOT NULL
) ENGINE=InnoDB;
