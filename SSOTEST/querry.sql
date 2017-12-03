

CREATE TABLE  TestLoginUser (
  id int NOT NULL,
  name varchar(100) NULL,
  password varchar(100) NULL,
  email varchar(100) NULL,
  contactno varchar(20) NULL,
  skill varchar(20) NULL,
  dt date default sysdate
); 
--drop table TestLoginUser
ALTER TABLE TestLoginUser ADD (
  CONSTRAINT id_pk PRIMARY KEY (id));
  --drop sequence TestLoginUser_seq
CREATE SEQUENCE TestLoginUser_seq START WITH 1;
--drop trigger TestLoginUser_on_insert
CREATE OR REPLACE TRIGGER TestLoginUserr_on_insert
  BEFORE INSERT OR UPDATE ON TestLoginUser 
  FOR EACH ROW
BEGIN
  SELECT TestLoginUser_seq.nextval
  INTO :new.id
  FROM dual;
END;

INSERT INTO TestLoginUser (name, password, email, contactno,skill) VALUES
('prateek', 'prateek', 'prateek@gmail.com', '9199291212','java');

INSERT INTO TestLoginUser (name, password, email, contactno,skill) VALUES
 ('sanjeet', 'sanjeet', 'roshan@gmail.com', '9953030303','java');

select * from TestLoginUser

create TABLE  TestSkill(
  id int NOT NULL,
  skill varchar(100) NULL,
  dt date default sysdate
); 

ALTER TABLE TestSkill ADD (
  CONSTRAINT skillid_pk PRIMARY KEY (id));
  
CREATE SEQUENCE TestSkill_seq START WITH 1;

CREATE OR REPLACE TRIGGER TestSkill_on_insert
  BEFORE INSERT ON TestSkill
  FOR EACH ROW
BEGIN
  SELECT TestSkill_seq.nextval
  INTO :new.id
  FROM dual;
END;

INSERT INTO TestSkill(skill) VALUES
('core java');
INSERT INTO TestSkill (skill) VALUES
('Adv java');
INSERT INTO TestSkill (skill) VALUES
('PlSql');
INSERT INTO TestSkill (skill) VALUES
('c#');
INSERT INTO TestSkill (skill) VALUES
('Dot Net');

select * from TestSkill;
--------------------------------------------------------------------------------------------------------
CREATE TABLE  TestQuestion (
  id int NOT NULL,
  question varchar(4000) NULL,
  skill varchar2(45) null,
 dt date default sysdate
); 

ALTER TABLE TestQuestion ADD (
  CONSTRAINT tqueid_pk PRIMARY KEY (id));
  
CREATE SEQUENCE Question_seq START WITH 1;

CREATE OR REPLACE TRIGGER Question_on_insert
  BEFORE INSERT ON TestQuestion
  FOR EACH ROW
BEGIN
  SELECT Question_seq.nextval
  INTO :new.id
  FROM dual;
END;

------------------------------------

CREATE TABLE  TestAnswer (
  id int NOT NULL,
  unid int unique,
  optOne varchar(400),
  ansOne varchar(1),
  optTwo varchar(400),
  ansTwo varchar(1),
  optThree varchar(400),
  ansThree varchar(1),
  optFour varchar(400),
  ansFour varchar(1),
  dt date default sysdate 
); 

ALTER TABLE TestAnswer ADD (
  CONSTRAINT AnsId1_pk PRIMARY KEY (id));
 
CREATE SEQUENCE TAnswer_seq START WITH 1;

CREATE OR REPLACE TRIGGER TAnswer_on_insert
  BEFORE INSERT ON TestAnswer
  FOR EACH ROW
BEGIN
  SELECT TAnswer_seq.nextval
  INTO :new.id
  FROM dual;
END;
------------------------------------------------
create or replace
PROCEDURE TestQuestionAnswer(
    valquestion IN SYSTEM.TESTQUESTION.QUESTION%TYPE,
    optOneVal IN SYSTEM.TESTANSWER.OPTONE%TYPE,
    optTwoVal IN SYSTEM.TESTANSWER.OPTTWO%TYPE,
    optThreeVal IN SYSTEM.TESTANSWER.OPTTHREE%TYPE,
    optFourVal IN SYSTEM.TESTANSWER.OPTFOUR%TYPE,
    ansOneVal IN SYSTEM.TESTANSWER.A%TYPE,
    ansTwoVal IN SYSTEM.TESTANSWER.B%TYPE,
    ansThreeVal IN SYSTEM.TESTANSWER.C%TYPE,
    ansFourVal IN SYSTEM.TESTANSWER.D%TYPE,
    skillVal IN SYSTEM.TESTQUESTION.skill%TYPE
)
as
 quesId int;
BEGIN
     insert into TestQuestion(question,skill)values(Valquestion,skillVal);
     select id into quesId from( select id from TestQuestion order by id desc )Where ROWNUM <= 1;
     insert into TestAnswer(unid,optOne,a,optTwo,b,
                            optThree,c,optFour,d  
                           )
                           values
                           (
                           quesId,optOneVal,ansOneVal,optTwoVal,ansTwoVal,
                            optThreeVal,ansThreeVal,optFourVal,ansFourVal
                           );

   --  message := 'Success';
     
END ;

---------------------------------------------------------
create or replace
FUNCTION TestQuestionAnsDel(unqid IN int)
RETURN number IS
   qusAnsId int := unqid;
BEGIN
   delete from TestQuestion where id=unqid;
   delete from TestAnswer where unid=unqid;
RETURN qusAnsId;
EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;

---------------------------------------------------------

create or replace
PROCEDURE TestQuestionAnswerUpdate(
    uniqID IN SYSTEM.TESTQUESTION.ID%TYPE,
    valquestion IN SYSTEM.TESTQUESTION.QUESTION%TYPE,
    optOneVal IN SYSTEM.TESTANSWER.OPTONE%TYPE,
    optTwoVal IN SYSTEM.TESTANSWER.OPTTWO%TYPE,
    optThreeVal IN SYSTEM.TESTANSWER.OPTTHREE%TYPE,
    optFourVal IN SYSTEM.TESTANSWER.OPTFOUR%TYPE,
    ansOneVal IN SYSTEM.TESTANSWER.A%TYPE,
    ansTwoVal IN SYSTEM.TESTANSWER.B%TYPE,
    ansThreeVal IN SYSTEM.TESTANSWER.C%TYPE,
    ansFourVal IN SYSTEM.TESTANSWER.D%TYPE,
    skillVal IN SYSTEM.TESTQUESTION.skill%TYPE
    
)
as
BEGIN
     update TestQuestion set 
            question=Valquestion,
            skill=skillVal where id=uniqID;
     update  TestAnswer set 
              unid=uniqID,
              optOne=optOneVal,
              a=ansOneVal,
              optTwo=optTwoVal,
              b=ansTwoVal,
              optThree=optThreeVal,
              c=ansThreeVal,
              optFour=optFourVal,
              d=ansFourVal where unid= uniqID;
              commit;
END;












