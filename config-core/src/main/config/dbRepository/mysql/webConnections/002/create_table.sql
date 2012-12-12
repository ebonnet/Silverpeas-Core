CREATE TABLE sb_webconnections_info (
    connectionid int(11) NOT NULL,
    userid int(11) NOT NULL,
    componentid varchar(50) NOT NULL,
    paramlogin varchar(100) NOT NULL,
    parampassword BLOB
) ENGINE=InnoDB;
