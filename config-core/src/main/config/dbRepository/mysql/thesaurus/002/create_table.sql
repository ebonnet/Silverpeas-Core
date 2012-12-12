CREATE TABLE sb_thesaurus_vocabulary (
    id int(11) NOT NULL,
    name varchar(100) NOT NULL,
    description varchar(2000) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_thesaurus_jargon (
    id int(11) NOT NULL,
    `type` int(11) NOT NULL,
    idvoca int(11) NOT NULL,
    iduser varchar(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_thesaurus_synonym (
    id int(11) NOT NULL,
    idvoca int(11) NOT NULL,
    idtree int(11) NOT NULL,
    idterm int(11) NOT NULL,
    name varchar(100)
) ENGINE=InnoDB;

