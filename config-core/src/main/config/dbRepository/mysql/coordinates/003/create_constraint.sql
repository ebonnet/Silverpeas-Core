alter table sb_coordinates_coordinates add 
	 constraint pk_coordinates_coordinates primary key
	(
		coordinatesid,
		nodeid,
		instanceid
	)   
;
