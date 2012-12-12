alter table sb_publication_info add 
	 constraint pk_publication_info primary key   
	(
		infoid
	)   
;

alter table sb_publication_infoattachment  add 
	 constraint pk_publication_infoattachment primary key   
	(
		infoattachmentid
	)   
;

alter table sb_publication_infoimage  add 
	 constraint pk_publication_infoimage primary key   
	(
		infoimageid
	)   
;

alter table sb_publication_infolink  add 
	 constraint pk_publication_infolink primary key   
	(
		infolinkid
	)   
;

alter table sb_publication_infotext  add 
	 constraint pk_publication_infotext primary key   
	(
		infotextid
	)   
;

alter table sb_publication_publi  add 
	 constraint pk_publication_publi primary key   
	(
		pubid
	)   
;

alter table sb_publication_publifather  add 
	 constraint pk_publication_publifather primary key   
	(
		pubid,
		nodeid,
		instanceid
	)   
;

alter table sb_seealso_link  add 
	 constraint pk_seealso_link primary key   
	(
		id
	)   
;

alter table sb_publication_publii18n  add 
	 constraint pk_publication_publii18n primary key   
	(
		id
	)   
;

alter table sb_thumbnail_thumbnail add 
	 constraint pk_thumbnail_thumbnail primary key   
	(
		objectid,
		objecttype,
		instanceid
	)   
;