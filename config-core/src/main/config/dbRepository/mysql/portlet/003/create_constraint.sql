alter table st_portletcolumn add constraint pk_portletcolumn primary key(id);
alter table st_portletcolumn add constraint fk_portletcolumn_1 foreign key(spaceid) references st_space (id);

alter table st_portletrow add constraint pk_portletrow primary key(id);
alter table st_portletrow add constraint fk_portletrow_1 foreign key(instanceid) references st_componentinstance(id);
alter table st_portletrow add constraint fk_portletrow_2 foreign key(portletcolumnid) references st_portletcolumn (id);

alter table st_portletstate add constraint pk_portletstate primary key(id);
alter table st_portletstate add constraint fk_portletstate_1 foreign key(portletrowid) references st_portletrow(id);
alter table st_portletstate add constraint fk_portletstate_2 foreign key(userid) references st_user(id);

alter table st_component add constraint pk_component primary key(id);
alter table st_component add constraint un_st_component_1 unique(componentname);

