CREATE TABLE sb_pdc_axis (
    id int(11) NOT NULL,
    rootid int(11) NOT NULL,
    name varchar(255) NOT NULL,
    axistype char(1) NOT NULL,
    axisorder int(11) NOT NULL,
    creationdate varchar(10),
    creatorid varchar(255),
    description varchar(1000),
    lang char(2)
) ENGINE=InnoDB;

CREATE TABLE sb_pdc_utilization (
    id int(11) NOT NULL,
    instanceid varchar(100) NOT NULL,
    axisid int(11) NOT NULL,
    basevalue int(11) NOT NULL,
    mandatory int(11) NOT NULL,
    variant int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_pdc_axisi18n (
    id int(11) NOT NULL,
    axisid int(11) NOT NULL,
    lang char(2) NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(1000)
) ENGINE=InnoDB;

CREATE TABLE sb_pdc_user_rights (
    axisid int(11) NOT NULL,
    valueid int(11) NOT NULL,
    userid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_pdc_group_rights (
    axisid int(11) NOT NULL,
    valueid int(11) NOT NULL,
    groupid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE pdcaxisvalue (
    valueid bigint NOT NULL,
    axisid bigint NOT NULL,
  primary key (valueId, axisId)
) ENGINE=InnoDB;

CREATE TABLE pdcclassification (
    id bigint NOT NULL,
    contentid varchar(255),
    instanceid varchar(255) NOT NULL,
    modifiable bool NOT NULL,
    nodeid varchar(255),
    primary key (id)
) ENGINE=InnoDB;

CREATE TABLE pdcclassification_pdcposition (
    pdcclassification_id bigint NOT NULL,
    positions_id bigint NOT NULL,
  primary key (pdcclassification_id, positions_id),
  unique (positions_id)
) ENGINE=InnoDB;

CREATE TABLE pdcposition (
    id bigint NOT NULL,
  primary key (id)
) ENGINE=InnoDB;

CREATE TABLE pdcposition_pdcaxisvalue (
    pdcposition_id bigint NOT NULL,
    axisvalues_valueid bigint NOT NULL,
    axisvalues_axisid bigint NOT NULL,
  primary key (pdcposition_id, axisvalues_valueid, axisvalues_axisid)
) ENGINE=InnoDB;

