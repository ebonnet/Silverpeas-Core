CREATE TABLE sb_comment_comment (
    commentid int(11) NOT NULL,
    commentownerid int(11) NOT NULL,
    commentcreationdate char(10) NOT NULL,
    commentmodificationdate char(10),
    commentcomment varchar(2000) NOT NULL,
    resourceid varchar(50) NOT NULL,
    instanceid varchar(50) NOT NULL,
    resourcetype varchar(50) NOT NULL
) ENGINE=InnoDB;
