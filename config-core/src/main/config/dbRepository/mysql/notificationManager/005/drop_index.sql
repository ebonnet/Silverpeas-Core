drop index in_notifaddress_1 ON st_notifaddress;

drop index in_notifpreference_1 ON st_notifpreference;

drop index in_notifsended ON st_notifsended;

drop index in_notifsendedreceiver ON st_notifsendedreceiver;

drop index idx_st_dn_userid ON st_delayednotification;
drop index idx_st_dn_channel ON st_delayednotification;

drop index idx_st_nr_resourceid ON st_notificationresource;

drop index idx_st_dnus_userid ON st_delayednotifusersetting;
drop index idx_st_dnus_channel ON st_delayednotifusersetting;
drop index idx_st_dnus_uc ON st_delayednotifusersetting;
