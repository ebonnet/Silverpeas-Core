alter table sb_contentmanager_instance add 
	 constraint pk_contentmanager_instance primary key
	(
		instanceid
	)   
;

alter table sb_contentmanager_content add 
	 constraint pk_contentmanager_content primary key
	(
		silvercontentid
	)   
;

alter table sb_contentmanager_content add 
	 constraint uqe_contentmanager_content unique
	(
		internalcontentid, contentinstanceid
	)   
;