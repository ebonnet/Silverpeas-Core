alter table sb_filesharing_ticket
add constraint pk_sb_filesharing_ticket primary key
	(
		keyfile
	)   
;

alter table sb_filesharing_history
add constraint pk_sb_filesharing_history primary key
	(
		id
	)   
;
