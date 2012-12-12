alter table st_notifdefaultaddress drop foreign key fk_notifdefaultaddress_1;
alter table st_notifpreference drop foreign key fk_notifpreference_1;
alter table st_notifpreference drop foreign key fk_notifpreference_2;
alter table st_notifaddress drop foreign key fk_notifaddress_1;
alter table st_notifaddress drop foreign key fk_notifaddress_2;

alter table st_notifchannel drop primary key;
alter table st_notifdefaultaddress drop primary key;
alter table st_notifpreference drop primary key;
alter table st_notifaddress drop primary key;

alter table st_notifsended drop primary key;

alter table st_notifsendedreceiver drop primary key;

alter table st_delayednotification drop primary key;
alter table st_delayednotification drop foreign key const_st_dn_fk_nrid;
alter table st_delayednotification drop foreign key const_st_dn_fk_userid;

alter table st_notificationresource drop primary key;

alter table st_delayednotifusersetting drop primary key;
alter table st_delayednotifusersetting drop foreign key const_st_dnus_fk_userid;
