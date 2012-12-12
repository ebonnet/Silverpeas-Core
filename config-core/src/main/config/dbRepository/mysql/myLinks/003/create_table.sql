CREATE TABLE sb_mylinks_link (
    linkid int(11) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255),
    url varchar(255) NOT NULL,
    visible int(11) NOT NULL,
    popup int(11) NOT NULL,
    userid varchar(50) NOT NULL,
    instanceid varchar(50),
    objectid varchar(50)
) ENGINE=InnoDB;
