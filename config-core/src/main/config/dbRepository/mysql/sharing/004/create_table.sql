CREATE TABLE sb_filesharing_ticket (
    shared_object bigint NOT NULL,
    componentid varchar(255) NOT NULL,
    creatorid varchar(50) NOT NULL,
    creationdate bigint NOT NULL,
    updateid varchar(50),
    updatedate bigint,
    enddate bigint,
    nbaccessmax int(11) NOT NULL,
    nbaccess int(11),
    keyfile varchar(255) NOT NULL,
    shared_object_type varchar(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_filesharing_history (
    id bigint NOT NULL,
    keyfile varchar(255) NOT NULL,
    downloaddate bigint NOT NULL,
    downloadip varchar(50) NOT NULL
) ENGINE=InnoDB;

