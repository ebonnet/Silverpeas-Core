CREATE TABLE sb_questioncontainer_comment (
    commentid int(11) NOT NULL,
    commentfatherid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    commentcomment varchar(2000),
    commentisanonymous int(11) NOT NULL,
    commentdate varchar(10) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_questioncontainer_qc (
    qcid int(11) NOT NULL,
    qctitle varchar(1000) NOT NULL,
    qcdescription varchar(2000),
    qccomment varchar(2000),
    qccreatorid varchar(100) NOT NULL,
    qccreationdate varchar(10) NOT NULL,
    qcbegindate varchar(10) NOT NULL,
    qcenddate varchar(10) NOT NULL,
    qcisclosed int(11) NOT NULL,
    qcnbvoters int(11) NOT NULL,
    qcnbquestionspage int(11) NOT NULL,
    qcnbmaxparticipations int(11),
    qcnbtriesbeforesolution int(11),
    qcmaxtime int(11),
    instanceid varchar(50) NOT NULL,
    anonymous int(11) NOT NULL
) ENGINE=InnoDB;
