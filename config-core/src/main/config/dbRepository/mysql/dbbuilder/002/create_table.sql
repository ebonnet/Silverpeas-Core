CREATE TABLE sr_dependencies (
    sr_package varchar(32) NOT NULL,
    sr_pkdependency varchar(32) NOT NULL
) ENGINE=InnoDB
/

CREATE TABLE sr_packages (
    sr_package varchar(32) NOT NULL,
    sr_version varchar(3) NOT NULL
) ENGINE=InnoDB
/

CREATE TABLE sr_rellog2 (
    sr_date date NOT NULL,
    sr_module varchar(100) NOT NULL,
    sr_action varchar(32) NOT NULL,
    sr_txt varchar(2000)
) ENGINE=InnoDB
/

CREATE TABLE sr_scripts (
    sr_item_id varchar(65) NOT NULL,
    sr_seq_num smallint NOT NULL,
    sr_text varchar(1100) NOT NULL
) ENGINE=InnoDB
/

CREATE TABLE sr_uninstitems (
    sr_item_id varchar(65) NOT NULL,
    sr_package varchar(32) NOT NULL,
    sr_action_tag varchar(32) NOT NULL,
    sr_item_order smallint NOT NULL,
    sr_file_name varchar(256) NOT NULL,
    sr_file_type varchar(32) NOT NULL,
    sr_delimiter varchar(256),
    sr_keep_delimiter smallint,
    sr_dbproc_name varchar(256)
) ENGINE=InnoDB
/
