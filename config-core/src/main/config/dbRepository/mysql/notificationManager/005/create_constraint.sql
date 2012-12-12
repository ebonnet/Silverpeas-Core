alter table st_notifchannel add	constraint pk_notifchannel primary key(id) ;

alter table st_notifdefaultaddress add constraint pk_st_notifdefaultaddress primary key(id);
alter table st_notifdefaultaddress add constraint fk_notifdefaultaddress_1 foreign key(userid) references st_user(id);

alter table st_notifpreference add constraint pk_notifaddr_component primary key(id);
alter table st_notifpreference add constraint fk_notifpreference_1 foreign key(componentinstanceid) references st_componentinstance (id);
alter table st_notifpreference add constraint fk_notifpreference_2 foreign key(userid) references st_user(id);

alter table st_notifaddress add constraint pk_notifaddress primary key(id);
alter table st_notifaddress add constraint fk_notifaddress_1 foreign key(notifchannelid) references st_notifchannel(id);
alter table st_notifaddress add	constraint fk_notifaddress_2 foreign key(userid) references st_user(id);

alter table st_notifsended add constraint pk_notifsended primary key(notifid);

alter table st_notifsendedreceiver add constraint pk_notifsendedreceiver primary key(notifid, userid);

alter table st_delayednotifusersetting add constraint const_st_dnus_pk primary key (id);
alter table st_delayednotifusersetting add constraint const_st_dnus_fk_userid foreign key (userid) references st_user(id);

alter table st_notificationresource add constraint const_st_nr_pk primary key (id);

alter table st_delayednotification add constraint const_st_dn_pk primary key (id);
alter table st_delayednotification add constraint const_st_dn_fk_nrid foreign key (notificationresourceid) references st_notificationresource(id);
alter table st_delayednotification add constraint const_st_dn_fk_userid foreign key (userid) references st_user(id);
