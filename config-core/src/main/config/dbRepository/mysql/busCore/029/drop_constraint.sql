alter table st_accesslevel drop index un_accesslevel_1;

alter table st_user drop index un_user_1;
alter table st_user drop index un_user_2;
alter table st_user drop foreign key fk_user_1;

alter table st_group drop foreign key fk_group_1;
alter table st_group drop index un_group_1;
alter table st_group drop index un_group_2;

alter table st_group_user_rel drop foreign key fk_group_user_rel_1;
alter table st_group_user_rel drop foreign key fk_group_user_rel_2;

alter table st_space drop foreign key fk_space_1;
alter table st_space drop foreign key fk_space_2;
alter table st_space drop index un_space_1;

alter table st_componentinstance drop foreign key fk_componentinstance_1;
alter table st_componentinstance drop foreign key fk_componentinstance_2;
alter table st_componentinstance drop index un_componentinstance_1;

alter table st_instance_data drop foreign key fk_instance_data_1;
alter table st_instance_data drop index un_instance_data_1;

alter table st_userrole drop foreign key fk_userrole_1;
alter table st_userrole drop index un_userrole_1;

alter table st_userrole_user_rel drop foreign key fk_userrole_user_rel_1;
alter table st_userrole_user_rel drop foreign key fk_userrole_user_rel_2;

alter table st_userrole_group_rel drop foreign key fk_userrole_group_rel_1;
alter table st_userrole_group_rel drop foreign key fk_userrole_group_rel_2;

alter table st_spaceuserrole drop foreign key fk_spaceuserrole_1;
alter table st_spaceuserrole drop index un_spaceuserrole_1;

alter table st_spaceuserrole_user_rel drop foreign key fk_spaceuserrole_user_rel_1;
alter table st_spaceuserrole_user_rel drop foreign key fk_spaceuserrole_user_rel_2;

alter table st_spaceuserrole_group_rel drop foreign key fk_spaceuserrole_group_rel_1;
alter table st_spaceuserrole_group_rel drop foreign key fk_spaceuserrole_group_rel_2;

alter table domainsp_group drop foreign key fk_domainsp_group_1;
alter table domainsp_group drop index un_domainsp_group_1;

alter table domainsp_user drop index un_domainsp_user_1;

alter table domainsp_group_user_rel drop foreign key fk_domainsp_group_user_rel_1;
alter table domainsp_group_user_rel drop foreign key fk_domainsp_group_user_rel_2;

alter table st_userfavoritespaces drop foreign key fk_userfavoritespaces_1;
alter table st_userfavoritespaces drop foreign key fk_userfavoritespaces_2;
alter table st_userfavoritespaces drop primary key;


alter table st_accesslevel drop primary key;
alter table st_user drop primary key;
alter table st_group drop primary key;
alter table st_group_user_rel drop primary key;
alter table st_space drop primary key;
alter table st_componentinstance drop primary key;
alter table st_instance_data drop primary key;
alter table st_userrole drop primary key;
alter table st_userrole_user_rel drop primary key;
alter table st_userrole_group_rel drop primary key;
alter table st_spaceuserrole drop primary key;
alter table st_spaceuserrole_user_rel drop primary key;
alter table st_spaceuserrole_group_rel drop primary key;
alter table domainsp_group drop primary key;
alter table domainsp_user drop primary key;
alter table st_domain drop primary key;
alter table domainsp_group_user_rel drop primary key;
alter table st_instance_modelused drop primary key;
alter table st_longtext drop primary key;
