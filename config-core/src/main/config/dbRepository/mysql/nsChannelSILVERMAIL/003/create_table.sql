CREATE TABLE st_silvermailmessage (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    folderid int(11),
    header varchar(255),
    sendername varchar(255),
    subject varchar(1024),
    body varchar(4000),
    source varchar(255),
    url varchar(255),
    datemsg varchar(255),
    readen int(11) NOT NULL
) ENGINE=InnoDB;
