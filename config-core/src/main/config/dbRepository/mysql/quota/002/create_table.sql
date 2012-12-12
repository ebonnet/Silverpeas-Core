CREATE TABLE st_quota (
    id bigint NOT NULL,
    quotatype varchar(50) NOT NULL,
    resourceid varchar(50) NOT NULL,
    mincount bigint NOT NULL,
    maxcount bigint NOT NULL,
    currentcount bigint NOT NULL,
    savedate timestamp NOT NULL
) ENGINE=InnoDB;