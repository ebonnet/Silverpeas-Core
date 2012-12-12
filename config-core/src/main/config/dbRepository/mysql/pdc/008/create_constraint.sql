alter table sb_pdc_axis add 
	 constraint pk_pdc_axis primary key   
	(
		id
	);

alter table sb_pdc_utilization add 
	 constraint pk_pdc_utilization primary key   
	(
		id
	);

alter table sb_pdc_axisi18n add 
	 constraint pk_pdc_axisi18n primary key   
	(
		id
	);

alter table sb_pdc_user_rights 
add constraint fk_pdc_user_rights_1 foreign key (axisid) references sb_pdc_axis(id);

alter table sb_pdc_user_rights 
add constraint fk_pdc_user_rights_2 foreign key (userid) references st_user(id);

alter table sb_pdc_group_rights 
add constraint fk_pdc_group_rights_1 foreign key (axisid) references sb_pdc_axis(id);

alter table sb_pdc_group_rights 
add constraint fk_pdc_group_rights_2 foreign key (groupid) references st_group(id);

alter table pdcclassification_pdcposition add constraint fk_pdcclassification_pdcposition_positionid
  foreign key (positions_id) references pdcposition(id);

alter table pdcclassification_pdcposition add constraint fk_pdcclassification_pdcposition_positionid_pdcclassificationid
  foreign key (pdcclassification_id) references pdcclassification(id);

alter table pdcposition_pdcaxisvalue add constraint fk_pdcposition_pdcaxisvalue_pdcpositionid
  foreign key (pdcposition_id) references pdcposition(id);
  
alter table pdcposition_pdcaxisvalue add constraint fk_pdcposition_pdcaxisvalue_pdcaxisvalueid
  foreign key (axisvalues_valueid, axisvalues_axisid) references pdcaxisvalue (valueid, axisid);

