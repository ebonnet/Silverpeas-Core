CREATE TABLE st_popupmessage (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    body varchar(4000),
    senderid varchar(10),
    sendername varchar(200),
    answerallowed char(1) DEFAULT '0',
    msgdate varchar(10),
    msgtime varchar(5)
) ENGINE=InnoDB;
