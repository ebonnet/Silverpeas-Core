alter table sb_formtemplate_templatefield drop foreign key fk_formtemplate_templatefield;
alter table sb_formtemplate_record drop foreign key fk_formtemplate_record;
alter table sb_formtemplate_textfield drop foreign key fk_formtemplate_textfield;
alter table sb_formtemplate_template drop index un_formtemplate_template;
alter table sb_formtemplate_templatefield drop index un_formtemplate_templatefield;
alter table sb_formtemplate_record drop index un_formtemplate_record;
alter table sb_formtemplate_templatefield drop primary key;
alter table sb_formtemplate_template drop primary key;
alter table sb_formtemplate_record drop primary key;
