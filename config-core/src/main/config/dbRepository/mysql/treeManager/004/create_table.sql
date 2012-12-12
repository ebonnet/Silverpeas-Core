CREATE TABLE sb_tree_tree (
    treeid int(11) NOT NULL,
    id int(11) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(1000),
    creationdate char(10) NOT NULL,
    creatorid varchar(255) NOT NULL,
    `path` varchar(1000) NOT NULL,
    levelnumber int(11) NOT NULL,
    fatherid int(11) NOT NULL,
    ordernumber int(11),
    lang char(2)
) ENGINE=InnoDB;

CREATE TABLE sb_tree_treei18n (
    id int(11) NOT NULL,
    treeid int(11) NOT NULL,
    nodeid int(11) NOT NULL,
    lang char(2) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(1000)
) ENGINE=InnoDB;
