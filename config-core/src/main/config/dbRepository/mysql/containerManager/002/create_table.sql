CREATE TABLE sb_containermanager_instance (
    instanceid int(11) NOT NULL,
    componentid varchar(100) NOT NULL,
    containertype varchar(100) NOT NULL,
    contenttype varchar(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_containermanager_links (
    positionid int(11) NOT NULL,
    containerinstanceid int(11) NOT NULL
) ENGINE=InnoDB;
