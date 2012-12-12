alter table sb_notation_notation
  add constraint pk_sb_notation_notation
  primary key (id);

alter table sb_notation_notation
  add constraint un_sb_notation_notation
  unique(instanceid, externalid, externaltype, author);