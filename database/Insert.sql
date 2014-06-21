Insert into COACH (Coach_ID, Username, Password, Phone) values (1, 'Chris', 'Chris', '+61490114575');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('1', 'Nelson', 'Oliviera', 'Nelson', '12', '+65356559554265');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('2', 'Matthew', 'Busch', 'Matt', '9', '+619178340654');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('3', 'Philip', 'Deignan', 'Irish', '33', '+676986542645');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('4', 'Andreas', 'Kloden', 'German', '2', '+986695664566');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('5', 'Robert', 'Hunter', 'Rob', '13', '+1416698865466');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('6', 'Manuel', 'Cardoso', 'Manny', '25', '+6156686654236');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('7', 'Samuel', 'Bewley', 'Sam', '42', '+63315653186653');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('8', 'Robert', 'McEwen', 'Robbie', '92', '+6326956648653');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('9', 'Jesse', 'Sergent', 'Jesse', '77', '+7653356215695');
INSERT INTO "CADENCE"."RIDER" (RIDER_ID, FIRST_NAME, LAST_NAME, NICKNAME, JERSEY_NO, PHONE) VALUES ('10', 'Ivan', 'Rovny', 'Ivan', '76', '+653335865513');

INSERT INTO "CADENCE"."MESSAGE" (MESSAGE_ID, MESSAGE, COACH_ID, MESSAGE_TS) VALUES ('1', 'Jesse, fall behind Manny.', '1', TO_DATE('2014-06-12 12:05:19', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "CADENCE"."MESSAGE" (MESSAGE_ID, MESSAGE, COACH_ID, MESSAGE_TS) VALUES ('2', 'Nelson, catch a breath. Go slow.', '1', TO_DATE('2014-06-12 12:06:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('2', '2');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '1');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '2');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '3');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '4');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '5');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '6');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '7');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '8');
INSERT INTO "CADENCE"."MESSAGE_RECIPIENT" (MESSAGE_ID, RIDER_ID) VALUES ('1', '9');

insert into race values (1, 'Tour Down Under', TO_DATE('2014/05/03', 'yyyy/mm/dd'));
insert into race_team values (1, 1, 1 );
insert into race_team values (1, 2, 2 );
insert into race_team values (1, 3, 3 );
insert into race_team values (1, 4, 4 );
insert into race_team values (1, 5, 5 );

