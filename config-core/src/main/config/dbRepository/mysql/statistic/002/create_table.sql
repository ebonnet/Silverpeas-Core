CREATE TABLE sb_statistic_history (
    datestat varchar(10) NOT NULL,
    heurestat varchar(10) NOT NULL,
    userid varchar(100) NOT NULL,
    objectid int(11) NOT NULL,
    componentid varchar(50) NOT NULL,
    actiontype int(11) NOT NULL,
    objecttype varchar(50) NOT NULL
) ENGINE=InnoDB;
