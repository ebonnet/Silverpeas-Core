alter table sb_version_document add 
	 constraint pk_version_document primary key 
	(
		documentid
	)
;

alter table sb_version_version add 
	 constraint pk_version_version primary key  
	(
		versionid
	)
;

alter table sb_doc_readers_acl add 
	 constraint pk_sb_doc_readers_acl primary key 
	(
		id
	)
;

alter table sb_doc_readers_acl_list add 
	 constraint pk_sb_doc_readers_acl_list primary key  
	(
		id
	)
;

alter table sb_doc_readers_acl_list add 
	 constraint fk_sb_doc_readers_acl_list foreign key  
	(
		accessid
	)
	references sb_doc_readers_acl (id)
;

