CREATE TABLE sb_contentmanager_content (
    silvercontentid int(11) NOT NULL,
    internalcontentid varchar(100) NOT NULL,
    contentinstanceid int(11) NOT NULL,
    authorid int(11) NOT NULL,
    creationdate date NOT NULL,
    begindate varchar(10),
    enddate varchar(10),
    isvisible int(11)
) ENGINE=InnoDB;

CREATE TABLE sb_contentmanager_instance (
    instanceid int(11) NOT NULL,
    componentid varchar(100) NOT NULL,
    containertype varchar(100) NOT NULL,
    contenttype varchar(100) NOT NULL
) ENGINE=InnoDB;
