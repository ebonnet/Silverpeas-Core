create index in_sb_pdc_axis_1 on sb_pdc_axis(axistype);

create index in_sb_pdc_utilization_1 on sb_pdc_utilization(basevalue);
create index in_sb_pdc_utilization_2 on sb_pdc_utilization(instanceid);

create index idx_pdcclassification_instanceid on pdcclassification(instanceid);
create index idx_pdcclassification_contentid on pdcclassification(contentid);

