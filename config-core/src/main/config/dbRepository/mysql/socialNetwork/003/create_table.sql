CREATE TABLE sb_sn_invitation (
    id int(11) NOT NULL,
    senderid int(11) NOT NULL,
    receiverid int(11) NOT NULL,
    message varchar(1000),
    invitationdate timestamp NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_sn_relationship (
    id int(11) NOT NULL,
    user1id int(11) NOT NULL,
    user2id int(11) NOT NULL,
    typerelationshipid int(11),
    acceptancedate timestamp NOT NULL,
    inviterid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_sn_typerelationship (
    id int(11) NOT NULL,
    designation varchar(10)
) ENGINE=InnoDB;

CREATE TABLE sb_sn_status (
    id int(11) NOT NULL,
    userid int(11) NOT NULL,
    creationdate timestamp NOT NULL,
    description varchar(1000) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_sn_externalaccount (
    profileid varchar(100) NOT NULL,
    networkid varchar(10) NOT NULL,
    silverpeasuserid varchar(50)
) ENGINE=InnoDB;
