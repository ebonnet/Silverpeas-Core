alter table sb_version_document			drop primary key;
alter table sb_version_version			drop primary key;
alter table sb_doc_readers_acl_list			drop primary key;
alter table sb_doc_readers_acl_list			drop foreign key fk_sb_doc_readers_acl_list;
alter table sb_doc_readers_acl			drop primary key;
