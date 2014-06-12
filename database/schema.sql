DROP TABLE statistics purge;
DROP TABLE message_recipient;
DROP TABLE message purge;
DROP TABLE coach purge;
DROP TABLE rider purge;

CREATE TABLE coach
(Coach_ID NUMBER(9),
 Username VARCHAR2(255),
 Password VARCHAR2(255),
 Phone VARCHAR2(30),
 CONSTRAINT coach_coach_id_PK PRIMARY KEY (Coach_id)
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

CREATE TABLE statistics
(Stat_ID NUMBER(9),
 Rider_ID NUMBER(9),
 Heart_Rate NUMBER(9),
 Speed NUMBER(9,2),
 Latitude NUMBER(9,9),
 Longitude NUMBER(9,9),
 Elevation NUMBER(9,9),
 Distance NUMBER(9,9),
 Cadence NUMBER(9,9),
 Power NUMBER(9,9),
 STAT_TS DATE,
 CONSTRAINT statistics_stat_id_PK PRIMARY KEY (Stat_ID),
 CONSTRAINT statistics_rider_id_FK FOREIGN KEY (Rider_ID) REFERENCES rider (Rider_ID)
);

CREATE TABLE message
(Message_ID NUMBER(9), 
 Message VARCHAR2(255),
 Coach_ID NUMBER(9),
 Message_TS DATE,
 CONSTRAINT message_msg_rider_id_PK PRIMARY KEY (Message_ID),
 CONSTRAINT message_coach_id_FK FOREIGN KEY (Coach_ID) REFERENCES coach (Coach_ID)
);

CREATE TABLE message_recipient
(Message_ID NUMBER(9), 
 Rider_ID NUMBER(9),
 CONSTRAINT merecepit_msg_rider_id_PK PRIMARY KEY (Message_ID, Rider_ID),
 CONSTRAINT merecepit_rider_id_FK FOREIGN KEY (Rider_ID) REFERENCES rider (Rider_ID),
 CONSTRAINT merecepit_message_id_FK FOREIGN KEY (Message_ID) REFERENCES message (Message_ID)
);

