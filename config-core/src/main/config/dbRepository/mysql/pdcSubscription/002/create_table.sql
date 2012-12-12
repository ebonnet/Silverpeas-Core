CREATE TABLE sb_pdc_subscription (
    id int(11) NOT NULL,
    name varchar(255) NOT NULL,
    ownerid int(11) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE sb_pdc_subscription_axis (
    id int(11) NOT NULL,
    pdcsubscriptionid int(11) NOT NULL,
    axisid int(11) NOT NULL,
    value varchar(100)
) ENGINE=InnoDB;
