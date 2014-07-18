DROP SEQUENCE CADENCE.HIBERNATE_SEQUENCE;
CREATE SEQUENCE HIBERNATE_SEQUENCE
      INCREMENT BY 1
      START WITH 100
      NOMAXVALUE
      NOCYCLE
      CACHE 10;

DROP TABLE message_recipient;
DROP TABLE message purge;
DROP TABLE note purge;
DROP TABLE race_team purge;
DROP TABLE race purge;
DROP TABLE statistic purge;
DROP TABLE rider purge;
DROP TABLE user_role purge;
DROP TABLE cadence_user purge;

CREATE TABLE cadence_user
(User_ID NUMBER(9),
 Username VARCHAR2(255),
 Password VARCHAR2(255),
 Phone VARCHAR2(30),
 CONSTRAINT cadenceuser_user_id_PK PRIMARY KEY (User_id),
 CONSTRAINT cadenceuser_username_CK unique (username)
);

CREATE TABLE user_role
( username VARCHAR2(255),
  role VARCHAR2(45),
  CONSTRAINT user_role_id_PK PRIMARY KEY (username, role),
  CONSTRAINT user_username_FK FOREIGN KEY (username) REFERENCES cadence_user (Username)
);

CREATE TABLE rider
(Rider_ID NUMBER(9),
 First_Name VARCHAR2(255),
 Last_Name VARCHAR2(255),
 Nickname VARCHAR2(255),
 Jersey_No VARCHAR2(255),
 Phone VARCHAR2(30),
 CONSTRAINT rider_rider_id_PK PRIMARY KEY (Rider_ID)
);

CREATE TABLE statistic
(Stat_ID NUMBER(9),
 Rider_ID NUMBER(9),
 Heart_Rate NUMBER(9),
 Speed NUMBER(9,2),
 Latitude NUMBER(9,5),
 Longitude NUMBER(9,5),
 Elevation NUMBER(9,5),
 Distance NUMBER(9,2),
 Cadence NUMBER(9,2),
 Power NUMBER(9,5),
 STAT_TS TIMESTAMP,
 CONSTRAINT statistics_stat_id_PK PRIMARY KEY (Stat_ID),
 CONSTRAINT statistics_rider_id_FK FOREIGN KEY (Rider_ID) REFERENCES rider (Rider_ID),
 CONSTRAINT statistics_stat_ts_UQ UNIQUE (Rider_ID, STAT_TS)
);

CREATE TABLE race
(Race_ID NUMBER(9), 
 Race_Name VARCHAR2(255),
 Race_Start TIMESTAMP,
 Race_End TIMESTAMP,
 ISONGOING char(1),
 Coach_ID NUMBER(9),
 CONSTRAINT race_id_PK PRIMARY KEY (race_ID),
 CONSTRAINT race_name_CK unique (race_name),
 CONSTRAINT race_coach_FK FOREIGN KEY (Coach_ID) REFERENCES cadence_user(User_ID)
);

CREATE TABLE race_team
(Race_ID NUMBER(9), 
 Rider_ID NUMBER(9),
 Jersey_ID NUMBER(9),
 CONSTRAINT race_team_race_rider_id_PK PRIMARY KEY (Race_ID, Rider_ID),
 CONSTRAINT race_team_rider_id_FK FOREIGN KEY (Rider_ID) REFERENCES rider (Rider_ID),
 CONSTRAINT race_team_race_id_FK  FOREIGN KEY (Race_ID)  REFERENCES race (Race_ID)
);

CREATE TABLE message
(Message_ID NUMBER(9), 
 Message VARCHAR2(255),
 User_ID NUMBER(9),
 Message_TS TIMESTAMP,
 Race_ID NUMBER(9),
 Sent char(1),
 CONSTRAINT message_msg_rider_id_PK PRIMARY KEY (Message_ID),
 CONSTRAINT message_user_id_FK FOREIGN KEY (User_ID) REFERENCES cadence_user (User_ID),
 CONSTRAINT message_race_id_FK FOREIGN KEY (Race_ID) REFERENCES Race (Race_ID)
);

CREATE TABLE message_recipient
(Message_ID NUMBER(9), 
 Rider_ID NUMBER(9),
 CONSTRAINT merecepit_msg_rider_id_PK PRIMARY KEY (Message_ID, Rider_ID),
 CONSTRAINT merecepit_rider_id_FK FOREIGN KEY (Rider_ID) REFERENCES rider (Rider_ID),
 CONSTRAINT merecepit_message_id_FK FOREIGN KEY (Message_ID) REFERENCES message (Message_ID)
);

CREATE TABLE note
(Note_ID NUMBER(9), 
 Note VARCHAR2(255),
 User_ID NUMBER(9),
 Note_TS TIMESTAMP,
 Race_id NUMBER(9),
 CONSTRAINT note_note_id_PK PRIMARY KEY (Note_ID),
 CONSTRAINT note_user_id_FK FOREIGN KEY (User_ID) REFERENCES cadence_user (User_ID),
 CONSTRAINT note_race_id_FK FOREIGN KEY (Race_ID) REFERENCES Race (Race_ID)
);

