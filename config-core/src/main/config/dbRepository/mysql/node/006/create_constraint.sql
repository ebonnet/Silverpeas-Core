alter table sb_node_node add 
	 constraint pk_node_node 
	 primary key
	(
		nodeid,
		instanceid
	)   
;
alter table sb_node_nodei18n add 
	 constraint pk_node_nodei18n 
	 primary key
	(
		id
	)   
;
