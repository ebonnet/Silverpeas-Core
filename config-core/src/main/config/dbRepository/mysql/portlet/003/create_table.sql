CREATE TABLE st_portletcolumn (
    id int(11) NOT NULL,
    spaceid int(11) NOT NULL,
    columnwidth varchar(10),
    nbcol int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_portletrow (
    id int(11) NOT NULL,
    instanceid int(11) NOT NULL,
    portletcolumnid int(11) NOT NULL,
    rowheight int(11) NOT NULL,
    nbrow int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_portletstate (
    id int(11) NOT NULL,
    state int(11) DEFAULT 0 NOT NULL,
    userid int(11) NOT NULL,
    portletrowid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE st_component (
    id int(11) NOT NULL,
    componentname varchar(100) NOT NULL,
    description varchar(400)
) ENGINE=InnoDB;

