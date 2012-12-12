CREATE TABLE st_servermessage (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    header varchar(255),
    subject varchar(1024),
    body varchar(4000),
    sessionid varchar(255),
    `type` char(1)
) ENGINE=InnoDB;
