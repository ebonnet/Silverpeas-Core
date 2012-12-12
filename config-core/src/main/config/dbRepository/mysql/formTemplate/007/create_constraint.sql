alter table sb_formtemplate_template add 
   constraint pk_formtemplate_template
   primary key ( templateid );
alter table sb_formtemplate_template add 
   constraint un_formtemplate_template
   unique ( externalid );

alter table sb_formtemplate_templatefield add 
   constraint pk_formtemplate_templatefield
   primary key ( templateid, fieldname );
alter table sb_formtemplate_templatefield add 
   constraint un_formtemplate_templatefield
   unique ( templateid, fieldindex );
alter table sb_formtemplate_templatefield add 
   constraint fk_formtemplate_templatefield
   foreign key ( templateid )
   references sb_formtemplate_template ( templateid );

alter table sb_formtemplate_record add 
   constraint pk_formtemplate_record
   primary key ( recordid );
alter table sb_formtemplate_record add 
   constraint un_formtemplate_record
   unique ( templateid, externalid, lang );
alter table sb_formtemplate_record add 
   constraint fk_formtemplate_record
   foreign key ( templateid )
   references sb_formtemplate_template ( templateid );

alter table sb_formtemplate_textfield add 
   constraint fk_formtemplate_textfield
   foreign key ( recordid )
   references sb_formtemplate_record ( recordid );

