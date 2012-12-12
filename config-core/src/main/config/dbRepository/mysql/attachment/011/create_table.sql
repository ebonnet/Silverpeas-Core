CREATE TABLE sb_attachment_attachment (
    attachmentid int(11) NOT NULL,
    attachmentphysicalname varchar(500) NOT NULL,
    attachmentlogicalname varchar(255) NOT NULL,
    attachmentdescription varchar(500),
    attachmenttype varchar(100),
    attachmentsize varchar(100),
    attachmentcontext varchar(500),
    attachmentforeignkey varchar(100) NOT NULL,
    instanceid varchar(50) NOT NULL,
    attachmentcreationdate varchar(10),
    attachmentauthor varchar(100),
    attachmenttitle varchar(100),
    attachmentinfo varchar(1000),
    attachmentordernum int(11) DEFAULT 0 NOT NULL,
    workerid varchar(50),
    cloneid varchar(50),
    lang char(2),
    reservationdate varchar(10),
    alertdate varchar(10),
    expirydate varchar(10),
    xmlform varchar(50)
) ENGINE=InnoDB;


CREATE TABLE sb_attachment_attachmenti18n (
    id int(11) NOT NULL,
    attachmentid int(11) NOT NULL,
    lang char(2) NOT NULL,
    attachmentphysicalname varchar(500) NOT NULL,
    attachmentlogicalname varchar(255) NOT NULL,
    attachmenttype varchar(100),
    attachmentsize varchar(100),
    instanceid varchar(50) NOT NULL,
    attachmentcreationdate varchar(10),
    attachmentauthor varchar(100),
    attachmenttitle varchar(100),
    attachmentinfo varchar(1000),
    xmlform varchar(50)
) ENGINE=InnoDB;
