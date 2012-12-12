create index jargon_iduser
on sb_thesaurus_jargon  (iduser);

create index synonym_termvoca
on sb_thesaurus_synonym  (idvoca, idtree, idterm);


