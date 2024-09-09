INSERT INTO usersDetails (id,firstName,lastName,birthdate,birthplace,sex,currentAddress) VALUES (1,'User2','Name2','2012-01-01','USA','M','1 address street');
INSERT INTO usersDetails (id,firstName,lastName,birthdate,birthplace,sex,currentAddress) VALUES (2,'User','Name','1999-08-11','udupi','Male','udupi');
INSERT INTO usersDetails (id,firstName,lastName,birthdate,birthplace,sex,currentAddress) VALUES (3,'User1','',null,'','','');


INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (1,'usersDetails','firstName');
INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (2,'usersDetails','lastName');
INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (3,'usersDetails','birthdate');
INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (4,'usersDetails','sex');
INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (5,'usersDetails','currentAddress');
INSERT INTO mandatoryFields (id,tableName,reqFieldName) VALUES (6,'usersDetails','birthplace');

INSERT INTO externalservice (id,servicename,url) VALUES (1,'userService','http://localhost:8080/api/v2/external/submituser');