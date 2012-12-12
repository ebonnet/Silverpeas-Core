CREATE TABLE sb_tagcloud_tagcloud (
    id int(11) NOT NULL,
    tag varchar(100) NOT NULL,
    label varchar(100) NOT NULL,
    instanceid varchar(50) NOT NULL,
    externalid varchar(50) NOT NULL,
    externaltype int(11) NOT NULL
) ENGINE=InnoDB;
