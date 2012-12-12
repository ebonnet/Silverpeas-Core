insert into st_accesslevel(id, name) values ('U', 'User');
insert into st_accesslevel(id, name) values ('A', 'Administrator');
insert into st_accesslevel(id, name) values ('G', 'Guest');
insert into st_accesslevel(id, name) values ('R', 'Removed');
insert into st_accesslevel(id, name) values ('K', 'KMManager');
insert into st_accesslevel(id, name) values ('D', 'DomainManager');

insert into st_user(id, specificId, domainId, lastName, login, accessLevel)
values             (0, '0', 0, 'Administrateur', '${ADMINLOGIN}', 'A');

insert into domainsp_user(id, lastName, login, password)
values             (0, 'Administrateur', '${ADMINLOGIN}', '${ADMINPASSWD}');

insert into st_domain(id, name, description, propFileName, className, authenticationServer, theTimeStamp, silverpeasServerURL)
values             (-1, 'internal', 'Do not remove - Used by Silverpeas engine', '-', '-', '-', '0', '');

insert into st_domain(id, name, description, propFileName, className, authenticationServer, theTimeStamp, silverpeasServerURL)
values             (0, 'domainSilverpeas', 'default domain for Silverpeas', 'com.stratelia.silverpeas.domains.domainSP', 'com.silverpeas.domains.silverpeasdriver.SilverpeasDomainDriver', 'autDomainSP', '0', '${URLSERVER}');
