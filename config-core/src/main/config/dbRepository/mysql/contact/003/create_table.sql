CREATE TABLE sb_contact_contact (
    contactid int(11) NOT NULL,
    contactfirstname varchar(1000),
    contactlastname varchar(1000),
    contactemail varchar(1000),
    contactphone varchar(20),
    contactfax varchar(20),
    userid varchar(100),
    contactcreationdate varchar(10) NOT NULL,
    contactcreatorid varchar(100) NOT NULL,
    instanceid varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_contact_contactfather (
    contactid int(11) NOT NULL,
    nodeid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_contact_info (
    infoid int(11) NOT NULL,
    contactid int(11) NOT NULL,
    modelid varchar(100) NOT NULL,
    instanceid varchar(50) NOT NULL
) ENGINE=InnoDB;



