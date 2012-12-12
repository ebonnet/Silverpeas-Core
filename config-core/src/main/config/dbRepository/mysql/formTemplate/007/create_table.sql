CREATE TABLE sb_formtemplate_template (
    templateid int(11) NOT NULL,
    externalid varchar(250) NOT NULL,
    templatename varchar(250)
) ENGINE=InnoDB;

CREATE TABLE sb_formtemplate_templatefield (
    templateid int(11) NOT NULL,
    fieldname varchar(50) NOT NULL,
    fieldindex int(11) NOT NULL,
    fieldtype varchar(50) NOT NULL,
    ismandatory smallint DEFAULT 0,
    isreadonly smallint DEFAULT 0,
    ishidden smallint DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE sb_formtemplate_record (
    recordid int(11) NOT NULL,
    templateid int(11) NOT NULL,
    externalid varchar(250) NOT NULL,
    lang char(2)
) ENGINE=InnoDB;


CREATE TABLE sb_formtemplate_textfield (
    recordid int(11) NOT NULL,
    fieldname varchar(100) NOT NULL,
    fieldvalue varchar(4000)
) ENGINE=InnoDB;