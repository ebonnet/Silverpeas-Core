CREATE TABLE sb_interest_center (
    id int(11) NOT NULL,
    name varchar(255) NOT NULL,
    criteria varchar(255),
    workspaceid varchar(100),
    peasid char(100),
    authorid char(10),
    afterdate varchar(10),
    beforedate varchar(10),
    ownerid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_interest_center_axis (
    id int(11) NOT NULL,
    icid int(11) NOT NULL,
    axisid int(11) NOT NULL,
    value varchar(100)
) ENGINE=InnoDB;
