alter table st_portletcolumn drop foreign key fk_portletcolumn_1;
alter table st_portletrow drop foreign key fk_portletrow_1;
alter table st_portletrow drop foreign key fk_portletrow_2;
alter table st_portletstate drop foreign key fk_portletstate_1;
alter table st_portletstate drop foreign key fk_portletstate_2;

alter table st_component drop index un_st_component_1;

alter table st_portletcolumn drop primary key;
alter table st_portletrow drop primary key;
alter table st_portletstate drop primary key;
alter table st_component drop primary key;
