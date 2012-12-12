CREATE TABLE sb_stat_connection (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    countconnection bigint NOT NULL,
    duration bigint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_stat_access (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    peastype varchar(50) NOT NULL,
    spaceid varchar(50) NOT NULL,
    componentid varchar(50) NOT NULL,
    countaccess bigint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_stat_sizedir (
    datestat varchar(10) NOT NULL,
    filedir varchar(256) NOT NULL,
    sizedir bigint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_stat_volume (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    peastype varchar(50) NOT NULL,
    spaceid varchar(50) NOT NULL,
    componentid varchar(50) NOT NULL,
    countvolume bigint NOT NULL
) ENGINE=InnoDB;


CREATE TABLE sb_stat_connectioncumul (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    countconnection bigint NOT NULL,
    duration bigint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_stat_accesscumul (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    peastype varchar(50) NOT NULL,
    spaceid varchar(50) NOT NULL,
    componentid varchar(50) NOT NULL,
    countaccess bigint NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_stat_sizedircumul (
    datestat varchar(10) NOT NULL,
    filedir varchar(256) NOT NULL,
    sizedir bigint NOT NULL
) ENGINE=InnoDB;


CREATE TABLE sb_stat_volumecumul (
    datestat varchar(10) NOT NULL,
    userid int(11) NOT NULL,
    peastype varchar(50) NOT NULL,
    spaceid varchar(50) NOT NULL,
    componentid varchar(50) NOT NULL,
    countvolume bigint NOT NULL
) ENGINE=InnoDB;
