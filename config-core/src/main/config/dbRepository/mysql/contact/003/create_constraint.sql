alter table sb_contact_contact  add 
	 constraint pk_contact_contact primary key  
	(
		contactid
	)   
;

alter table sb_contact_contactfather  add 
	 constraint pk_contact_contactfather primary key   
	(
		contactid,
		nodeid
	)   
;

alter table sb_contact_info  add 
	 constraint pk_contact_info primary key   
	(
		infoid
	)   
;
