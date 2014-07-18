Insert into CADENCE_USER (USER_ID, Username, Password, Phone) values (1, 'Chris', 'Chris', '+61490114575');
Insert into CADENCE_USER (USER_ID, Username, Password, Phone) values (2, 'ted',   'demo',  '+61490114576');
Insert into CADENCE_USER (USER_ID, Username, Password, Phone) values (3, 'bob',   'demo',  '+61490114577');
Insert into CADENCE_USER (USER_ID, Username, Password, Phone) values (4, 'jim',   'demo',  '+61490114578');
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

INSERT INTO "CADENCE"."MESSAGE" (MESSAGE_ID, MESSAGE, USER_ID, MESSAGE_TS, SENT) VALUES ('1', 'Jesse, fall behind Manny.',        '1', TO_DATE('2014-06-12 12:05:19', 'YYYY-MM-DD HH24:MI:SS'), 'N');
INSERT INTO "CADENCE"."MESSAGE" (MESSAGE_ID, MESSAGE, USER_ID, MESSAGE_TS, SENT) VALUES ('2', 'Nelson, catch a breath. Go slow.', '1', TO_DATE('2014-06-12 12:06:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y');

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

insert into race (Race_ID, Race_Name, Race_Start, Race_End, ISONGOING, Coach_ID) values (1, 'Tour Down Under', TO_DATE('2014/05/03', 'yyyy/mm/dd'), null, 'Y', 1);
insert into race_team values (1, 1, 1 );
insert into race_team values (1, 2, 2 );
insert into race_team values (1, 3, 3 );
insert into race_team values (1, 4, 4 );
insert into race_team values (1, 5, 5 );

Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (1,1,80,50.03, -34.926870,138.599825,341.6000061,84.212,18.45,20.05,TO_DATE('2014-06-18 06:41:58','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (2,1,83,50.038,-34.927043,138.599934,341,84.219,18.458,20.059,TO_DATE('2014-06-18 06:42:08','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (3,1,81,50.045,-34.927210,138.599993,340.3999939,84.227,18.467,20.068,TO_DATE('2014-06-18 06:42:18','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (4,2,85,50.052,-34.927220,138.599993,341.6000061,84.234,18.475,20.077,TO_DATE('2014-06-18 06:41:59','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (5,2,80,50.06,-34.927240,138.599993,340.7999878,84.241,18.483,20.085,TO_DATE('2014-06-18 06:42:09','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (6,2,86,50.067,-34.927250,138.599993,340.6000061,84.248,18.491,20.094,TO_DATE('2014-06-18 06:42:19','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (7,3,83,50.075,-34.927226,138.599993,341.6000061,84.255,18.499,20.103,TO_DATE('2014-06-18 06:42:00','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (8,3,81,50.083,-34.927260,138.599993,340.6000061,84.261,18.506,20.111,TO_DATE('2014-06-18 06:42:10','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (9,3,81,50.091,-34.927270,138.599993,340.7999878,84.268,18.512,20.119,TO_DATE('2014-06-18 06:42:20','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (10,4,84,50.099,-34.927280,138.599993,341.6000061,84.274,18.516,20.128,TO_DATE('2014-06-18 06:42:01','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (11,4,89,50.107,-34.927290,138.599993,340.3999939,84.281,18.52,20.137,TO_DATE('2014-06-18 06:42:11','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (12,4,82,50.115,-34.927120,138.599993,340.7999878,84.287,18.524,20.145,TO_DATE('2014-06-18 06:42:21','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (13,5,86,50.122,-34.927220,138.599993,340.3999939,84.293,18.529,20.154,TO_DATE('2014-06-18 06:42:02','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (14,5,86,50.13,-34.927320,138.599993,340.2000122,84.299,18.535,20.162,TO_DATE('2014-06-18 06:42:12','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (15,5,83,50.138,-34.927420,138.599993,340.7999878,84.305,18.54,20.171,TO_DATE('2014-06-18 06:42:22','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (16,6,81,50.146,-34.927520,138.599993,340.3999939,84.311,18.546,20.179,TO_DATE('2014-06-18 06:42:03','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (17,6,87,50.154,-34.927620,138.599993,340.3999939,84.319,18.552,20.187,TO_DATE('2014-06-18 06:42:13','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (18,6,88,50.162,-34.927720,138.599993,340.6000061,84.327,18.558,20.196,TO_DATE('2014-06-18 06:42:23','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (19,7,82,50.17,-34.927280,138.599993,340.3999939,84.335,18.565,20.204,TO_DATE('2014-06-18 06:42:04','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (20,7,82,50.178,-34.927920,138.599993,340.2000122,84.343,18.572,20.213,TO_DATE('2014-06-18 06:42:14','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (21,7,86,50.186,-34.927020,138.599993,340.6000061,84.351,18.579,20.221,TO_DATE('2014-06-18 06:42:24','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (22,8,83,50.195,-34.922120,138.599993,340.6000061,84.358,18.586,20.23,TO_DATE('2014-06-18 06:42:05','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (23,8,83,50.203,-34.925220,138.599993,340.2000122,84.366,18.594,20.238,TO_DATE('2014-06-18 06:42:15','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (24,8,84,50.212,-34.927220,138.599993,340.6000061,84.372,18.601,20.246,TO_DATE('2014-06-18 06:42:25','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (25,9,86,50.22,-34.927920,138.599993,340.7999878,84.377,18.608,20.254,TO_DATE('2014-06-18 06:42:06','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (26,9,84,50.229,-34.927220,138.599993,340.2000122,84.38,18.616,20.262,TO_DATE('2014-06-18 06:42:16','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (27,9,84,50.237,-34.925220,138.599993,340.3999939,84.382,18.623,20.271,TO_DATE('2014-06-18 06:42:26','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (28,10,87,50.246,-34.937220,138.599993,340.7999878,84.384,18.63,20.279,TO_DATE('2014-06-18 06:42:07','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (29,10,85,50.255,-34.977220,138.599993,340.2000122,84.386,18.637,20.287,TO_DATE('2014-06-18 06:42:17','YYYY-MM-DD HH24:MI:SS'));
Insert into Statistic (STAT_ID,RIDER_ID,HEART_RATE,SPEED,LATITUDE,LONGITUDE,ELEVATION,DISTANCE,CADENCE,POWER,STAT_TS) VALUES (30,10,88,50.264,-34.927230,138.599993,340.3999939,84.388,18.645,20.294,TO_DATE('2014-06-18 06:42:27','YYYY-MM-DD HH24:MI:SS'));

INSERT INTO user_role (username, ROLE) VALUES ('Chris', 'ROLE_ADMIN');
INSERT INTO user_role (username, ROLE) VALUES ('ted',   'ROLE_ADMIN');
INSERT INTO user_role (username, ROLE) VALUES ('ted',   'ROLE_USER');
INSERT INTO user_role (username, ROLE) VALUES ('bob',   'ROLE_USER');
INSERT INTO user_role (username, ROLE) VALUES ('jim',   'ROLE_ADMIN');

INSERT INTO note (note_id, note, user_id, note_ts, race_id) values (1, '32 minutes into the race', 1, TO_DATE('2014-06-18 06:41:58','YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO note (note_id, note, user_id, note_ts, race_id) values (2, 'We are going to win',      1, TO_DATE('2014-06-18 07:41:58','YYYY-MM-DD HH24:MI:SS'), 1);

commit;
