alter table sb_question_answer add 
	 constraint pk_question_answer primary key   
	(
		answerid
	)
;

alter table sb_question_question add 
	 constraint pk_question_question primary key   
	(
		questionid
	)
;

alter table sb_question_questionresult add 
	 constraint pk_question_questionresult primary key   
	(
		qrid
	)
;

alter table sb_question_score add 
	 constraint pk_question_score primary key   
	(
		scoreid
	)
;
