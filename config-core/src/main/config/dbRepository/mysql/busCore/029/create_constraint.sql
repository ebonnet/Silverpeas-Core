alter table st_accesslevel  add constraint pk_accesslevel primary key (id);
alter table st_accesslevel add constraint un_accesslevel_1 unique (name);

alter table st_user add constraint pk_user primary key (id);
alter table st_user add constraint unique un_user_1 (specificid(200), domainid);
alter table st_user add constraint un_user_2 unique(login, domainid);
alter table st_user add constraint fk_user_1 foreign key(accesslevel) references st_accesslevel(id);

alter table st_group  add constraint pk_group primary key (id);
alter table st_group add constraint un_group_1 unique(specificid(200), domainid);
alter table st_group add constraint un_group_2 unique(supergroupid, name, domainid);
alter table st_group add constraint fk_group_1 foreign key (supergroupid) references st_group(id);

alter table st_group_user_rel  add constraint pk_group_user_rel primary key (groupid, userid);
alter table st_group_user_rel add constraint fk_group_user_rel_1 foreign key (groupid) references st_group(id);
alter table st_group_user_rel add constraint fk_group_user_rel_2 foreign key (userid) references st_user(id);

alter table st_space  add constraint pk_space primary key (id);
alter table st_space add constraint un_space_1 unique(domainfatherid, name);
alter table st_space add constraint fk_space_1 foreign key (createdby) references st_user(id);
alter table st_space add constraint fk_space_2 foreign key (domainfatherid) references st_space(id);

alter table st_componentinstance  add constraint pk_componentinstance primary key (id);
alter table st_componentinstance add constraint un_componentinstance_1 unique(spaceid, name);
alter table st_componentinstance add constraint fk_componentinstance_1 foreign key (spaceid) references st_space(id);
alter table st_componentinstance add constraint fk_componentinstance_2 foreign key (createdby) references st_user(id);

alter table st_instance_data  add constraint pk_instance_data primary key (id);
alter table st_instance_data add constraint un_instance_data_1 unique(componentid, name);
alter table st_instance_data add constraint fk_instance_data_1 foreign key (componentid) references st_componentinstance(id);

alter table st_userrole  add constraint pk_userrole primary key (id);
alter table st_userrole add constraint un_userrole_1 unique(instanceid, rolename, isinherited, objectid);
alter table st_userrole add constraint fk_userrole_1 foreign key (instanceid) references st_componentinstance(id);

alter table st_userrole_user_rel  add constraint pk_userrole_user_rel primary key (userroleid, userid);
alter table st_userrole_user_rel add constraint fk_userrole_user_rel_1 foreign key (userroleid) references st_userrole(id);
alter table st_userrole_user_rel add constraint fk_userrole_user_rel_2 foreign key (userid) references st_user(id);

alter table st_userrole_group_rel  add constraint pk_userrole_group_rel primary key (userroleid, groupid);
alter table st_userrole_group_rel add constraint fk_userrole_group_rel_1 foreign key (userroleid) references st_userrole(id);
alter table st_userrole_group_rel add constraint fk_userrole_group_rel_2 foreign key (groupid) references st_group(id);

alter table st_spaceuserrole  add constraint pk_spaceuserrole primary key (id);
alter table st_spaceuserrole add constraint un_spaceuserrole_1 unique(spaceid, rolename, isinherited);
alter table st_spaceuserrole add constraint fk_spaceuserrole_1 foreign key (spaceid) references st_space(id);

alter table st_spaceuserrole_user_rel  add constraint pk_spaceuserrole_user_rel primary key (spaceuserroleid, userid);
alter table st_spaceuserrole_user_rel add constraint fk_spaceuserrole_user_rel_1 foreign key (spaceuserroleid) references st_spaceuserrole(id);
alter table st_spaceuserrole_user_rel add constraint fk_spaceuserrole_user_rel_2 foreign key (userid) references st_user(id);

alter table st_spaceuserrole_group_rel  add constraint pk_spaceuserrole_group_rel primary key (spaceuserroleid, groupid);
alter table st_spaceuserrole_group_rel add constraint fk_spaceuserrole_group_rel_1 foreign key (spaceuserroleid) references st_spaceuserrole(id);
alter table st_spaceuserrole_group_rel add constraint fk_spaceuserrole_group_rel_2 foreign key (groupid) references st_group(id);

alter table domainsp_group  add constraint pk_domainsp_group primary key (id);
alter table domainsp_group add constraint un_domainsp_group_1 unique(supergroupid, name);
alter table domainsp_group add constraint fk_domainsp_group_1 foreign key (supergroupid) references domainsp_group(id);

alter table domainsp_user  add constraint pk_domainsp_user primary key (id);
alter table domainsp_user add constraint un_domainsp_user_1 unique(login);

alter table domainsp_group_user_rel add constraint fk_domainsp_group_user_rel_1 foreign key (groupid) references domainsp_group(id);
alter table domainsp_group_user_rel add constraint fk_domainsp_group_user_rel_2 foreign key (userid) references domainsp_user(id);

alter table st_domain  add constraint pk_st_domain primary key (id);

alter table domainsp_group_user_rel  add constraint pk_domainsp_group_user_rel primary key (groupid,userid);

alter table st_longtext add constraint pk_st_longtext primary key (id,ordernum);

alter table st_instance_modelused add 
	 constraint pk_st_instance_modelused primary key 
	(
		instanceid,
		modelid,
		objectid
	)
;

alter table st_userfavoritespaces add constraint pk_userfavoritespaces primary key (id);
alter table st_userfavoritespaces add constraint fk_userfavoritespaces_1 foreign key (userid) references st_user(id);
alter table st_userfavoritespaces add constraint fk_userfavoritespaces_2 foreign key (spaceid) references st_space(id);
