alter table sb_pdc_user_rights drop foreign key fk_pdc_user_rights_1;
alter table sb_pdc_user_rights drop foreign key fk_pdc_user_rights_2;

alter table sb_pdc_group_rights drop foreign key fk_pdc_group_rights_1;
alter table sb_pdc_group_rights drop foreign key fk_pdc_group_rights_2;

alter table sb_pdc_axis	drop primary key;
alter table sb_pdc_utilization	drop primary key;
alter table sb_pdc_axisi18n	drop primary key;

alter table pdcposition_pdcaxisvalue drop foreign key fk_pdcposition_pdcaxisvalue_axisvaluesid;
alter table pdcposition_pdcaxisvalue drop foreign key fk_pdcposition_pdcaxisvalue_pdcpositionid;
alter table pdcclassification_pdcposition drop foreign key fk_pdcclassification_pdcposition_positionid;
alter table pdcclassification_pdcposition drop foreign key fk_pdcclassification_pdcposition_positionid_pdcclassificationid;
