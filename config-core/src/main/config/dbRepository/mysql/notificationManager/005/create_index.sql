CREATE INDEX in_notifaddress_1 ON st_notifaddress(userId);

CREATE UNIQUE INDEX in_notifpreference_1 ON st_notifpreference(userId, componentInstanceId, messageType);

CREATE INDEX in_notifsended ON st_notifsended(notifId);

CREATE INDEX in_notifsendedreceiver ON st_notifsendedreceiver(notifId);

CREATE INDEX idx_st_dnus_userid ON st_delayednotifusersetting(userId);
CREATE INDEX idx_st_dnus_channel ON st_delayednotifusersetting(channel);
CREATE UNIQUE INDEX idx_st_dnus_uc ON st_delayednotifusersetting(userId, channel);

CREATE INDEX idx_st_nr_resourceid ON st_notificationresource(resourceId);

CREATE INDEX idx_st_dn_userid ON st_delayednotification(userId);
CREATE INDEX idx_st_dn_channel ON st_delayednotification(channel);