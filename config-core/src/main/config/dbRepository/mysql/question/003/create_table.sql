CREATE TABLE sb_question_answer (
    answerid int(11) NOT NULL,
    questionid int(11) NOT NULL,
    answerlabel varchar(1000),
    answernbpoints int(11),
    answerissolution int(11) NOT NULL,
    answercomment varchar(2000),
    answernbvoters int(11) NOT NULL,
    answerisopened int(11) NOT NULL,
    answerimage varchar(100),
    answerquestionlink varchar(100)
) ENGINE=InnoDB;

CREATE TABLE sb_question_question (
    questionid int(11) NOT NULL,
    qcid int(11) NOT NULL,
    questionlabel varchar(1000) NOT NULL,
    questiondescription varchar(2000),
    questionclue varchar(2000),
    questionimage varchar(1000),
    questionisqcm int(11) NOT NULL,
    questiontype int(11) NOT NULL,
    questionisopen int(11) NOT NULL,
    questioncluepenalty int(11) NOT NULL,
    questionmaxtime int(11) NOT NULL,
    questiondisplayorder int(11) NOT NULL,
    questionnbpointsmin int(11) NOT NULL,
    questionnbpointsmax int(11) NOT NULL,
    instanceid varchar(50) NOT NULL,
    style varchar(50)
) ENGINE=InnoDB;

CREATE TABLE sb_question_questionresult (
    qrid int(11) NOT NULL,
    questionid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    answerid int(11) NOT NULL,
    qropenanswer varchar(2000),
    qrnbpoints int(11) NOT NULL,
    qrpolldate varchar(10) NOT NULL,
    qrelapsedtime varchar(100),
    qrparticipationid int(11)
) ENGINE=InnoDB;

CREATE TABLE sb_question_score (
    scoreid int(11) NOT NULL,
    qcid int(11) NOT NULL,
    userid varchar(100) NOT NULL,
    scoreparticipationid int(11) NOT NULL,
    scorescore int(11) NOT NULL,
    scoreelapsedtime varchar(100),
    scoreparticipationdate varchar(10) NOT NULL,
    scoresuggestion varchar(2000)
) ENGINE=InnoDB;
