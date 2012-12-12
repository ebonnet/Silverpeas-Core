ALTER TABLE uniqueid  ADD 
	CONSTRAINT pk_uniqueid PRIMARY KEY   
	(
		tableName
	);

ALTER TABLE personalization  ADD 
	CONSTRAINT pk_personalization PRIMARY KEY   
	(
		id
	);

ALTER TABLE readingcontrol  ADD 
	CONSTRAINT pk_readingcontrol PRIMARY KEY   
	(
		pubId, actorId, space, componentName
	);


ALTER TABLE subscribe  ADD 
	CONSTRAINT pk_subscribe PRIMARY KEY   
	(
		actorId, nodeId, space, componentName
	);

ALTER TABLE model_contact  ADD 
	CONSTRAINT pk_model_contact PRIMARY KEY   
	(
		id
	);

ALTER TABLE model  ADD 
	CONSTRAINT pk_model PRIMARY KEY   
	(
		id
	);

ALTER TABLE favorit  ADD 
	CONSTRAINT pk_favorit PRIMARY KEY   
	(
		actorId, nodeId, space, componentName
	);

ALTER TABLE calendarjournal  ADD 
	CONSTRAINT pk_calendarjournal PRIMARY KEY   
	(
		id
	);

ALTER TABLE calendarcategory  ADD 
	CONSTRAINT pk_calendarcategory PRIMARY KEY   
	(
		categoryId
	);

ALTER TABLE calendarjournalattendee  ADD 
	CONSTRAINT pk_calendarjournalattendee PRIMARY KEY   
	(
		journalId, userId
	);


ALTER TABLE calendarjournalcategory  ADD 
	CONSTRAINT pk_calendarjournalcategory PRIMARY KEY   
	(
		journalId, categoryId
	);

ALTER TABLE calendartodo  ADD 
	CONSTRAINT pk_calendartodo PRIMARY KEY   
	(
		id
	);

ALTER TABLE calendartodoattendee  ADD 
	CONSTRAINT pk_calendartodoattendee PRIMARY KEY   
	(
		todoId, userId
	);

ALTER TABLE st_formdesigner_formdesign  ADD 
	CONSTRAINT pk_st_formdesigner_formdesign PRIMARY KEY   
	(
		ID
	);

ALTER TABLE st_formdesigner_connectors  ADD 
	CONSTRAINT pk_st_formdesigner_connectors PRIMARY KEY   
	(
		ID
	);

ALTER TABLE st_formeditor_formedited  ADD 
	CONSTRAINT pk_st_formeditor_formedited PRIMARY KEY   
	(
		ID
	);

ALTER TABLE st_formeditor_formediteddata  ADD 
	CONSTRAINT pk_st_formeditor_fed PRIMARY KEY   
	(
		ID
	);

ALTER TABLE sb_agenda_import_settings ADD 
	CONSTRAINT pk_sb_agenda_import_settings_set PRIMARY KEY 
	(
		userid
	);


