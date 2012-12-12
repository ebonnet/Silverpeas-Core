CREATE TABLE sb_node_node (
    nodeid int(11) NOT NULL,
    nodename varchar(1000) NOT NULL,
    nodedescription varchar(2000),
    nodecreationdate varchar(10) NOT NULL,
    nodecreatorid varchar(100) NOT NULL,
    nodepath varchar(1000) NOT NULL,
    nodelevelnumber int(11) NOT NULL,
    nodefatherid int(11) NOT NULL,
    modelid varchar(1000),
    nodestatus varchar(1000),
    instanceid varchar(50) NOT NULL,
    `type` varchar(50),
    ordernumber int(11) DEFAULT 0,
    lang char(2),
    rightsdependson int(11) DEFAULT -1  NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_node_nodei18n (
    id int(11) NOT NULL,
    nodeid int(11) NOT NULL,
    lang char(2) NOT NULL,
    nodename varchar(1000) NOT NULL,
    nodedescription varchar(2000)
) ENGINE=InnoDB;
