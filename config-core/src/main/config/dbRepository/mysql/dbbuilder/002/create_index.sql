create index r_24_fk on sr_uninstitems (sr_package)
/
create index sr_uninstitems_i1 on sr_uninstitems (sr_package, sr_action_tag, sr_item_order)
/
create index relation_46_fk on sr_scripts (sr_item_id)
/
create index sr_item_id on sr_scripts (sr_item_id, sr_seq_num)
/
create unique index in_dependencies_1 on sr_dependencies (sr_package, sr_pkdependency)
/
