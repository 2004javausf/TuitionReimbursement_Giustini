CREATE TABLE EMPLOYEES(
USER_ID NUMBER PRIMARY KEY,
USER_FNAME VARCHAR2 (25),
USER_LNAME VARCHAR2 (25),
TITLE NUMBER,
USERNAME VARCHAR2 (25),
PASSWORD VARCHAR2 (25)
);

CREATE TABLE TITLE(
TITLE_ID NUMBER PRIMARY KEY,
TITLE_DESCRIPTION VARCHAR2 (25)
);

CREATE TABLE CLAIM(
CLAIM_ID NUMBER PRIMARY KEY,
FNAME VARCHAR2 (25),
LNAME VARCHAR2 (25),
EVENT_TYPE NUMBER,
EVENT_DESCRIPTION VARCHAR2 (250),
EVENT_DATE DATE,
EVENT_TIME NUMBER,
EVENT_LOCATION VARCHAR2 (100),
EVENT_COST NUMBER,
GRADE_TYPE NUMBER,
EVENT_JUSTIFY VARCHAR2 (250),
OPT_FILE BLOB DEFAULT EMPTY_BLOB(),
APPROVED_AMT NUMBER,
CLAIM_STATUS NUMBER
);

CREATE TABLE EVENT(
EVENT_TYPE_ID NUMBER PRIMARY KEY,
EVENT_TYPE_DESCRIPTION VARCHAR2 (25)
);

CREATE TABLE GRADES(
GRADE_FORMAT_ID NUMBER PRIMARY KEY,
GRADE_FORMAT_DESCRIPTION VARCHAR2 (50),
GRADE_PASS NUMBER);

CREATE TABLE CLAIM_STATUS(
STATUS_ID NUMBER PRIMARY KEY,
STATUS_DESCRIPTION VARCHAR2 (25))
;

CREATE TABLE MESSAGE (
MESSAGE_ID NUMBER PRIMARY KEY,
SUBMIT_DATE TIMESTAMP,
SENDER_ID NUMBER,
RECIPIENT_ID NUMBER,
CLAIM_ID NUMBER,
MESSAGE_DESCRIPTION VARCHAR2 (250)
);

ALTER TABLE EMPLOYEES
ADD CONSTRAINT FK_TITLE_ID
FOREIGN KEY (TITLE) REFERENCES TITLE(TITLE_ID);

ALTER TABLE CLAIM
ADD CONSTRAINT FK_EVENT_TYPE_ID
FOREIGN KEY (EVENT_TYPE) REFERENCES EVENT(EVENT_TYPE_ID);

ALTER TABLE CLAIM
ADD CONSTRAINT FK_GRADE_TYPE_ID
FOREIGN KEY (GRADE_TYPE) REFERENCES GRADES(GRADE_FORMAT_ID);

ALTER TABLE CLAIM
ADD CONSTRAINT FK_CLAIM_STATUS_ID
FOREIGN KEY (CLAIM_STATUS) REFERENCES CLAIM_STATUS(STATUS_ID);

CREATE SEQUENCE USER_SEQUENCE;
CREATE OR REPLACE TRIGGER USER_ON_INSERT
BEFORE INSERT ON EMPLOYEES
FOR EACH ROW
BEGIN
SELECT USER_SEQUENCE.NEXTVAL
INTO :NEW.USER_ID
FROM DUAL;
END;

CREATE SEQUENCE CLAIM_SEQUENCE;
CREATE OR REPLACE TRIGGER CLAIM_ON_INSERT
BEFORE INSERT ON CLAIM
FOR EACH ROW
BEGIN
SELECT CLAIM_SEQUENCE.NEXTVAL
INTO :NEW.CLAIM_ID
FROM DUAL;
END;

CREATE SEQUENCE MESSAGE_SEQUENCE;
CREATE OR REPLACE TRIGGER MESSAGE_ON_INSERT
BEFORE INSERT ON MESSAGE
FOR EACH ROW
BEGIN
SELECT MESSAGE_SEQUENCE.NEXTVAL
INTO :NEW.MESSAGE_ID
FROM DUAL;
END;

ALTER TABLE EMPLOYEES
ADD AVAILABLE_AMT NUMBER;

ALTER TABLE EVENT
ADD EVENT_REIMBURSEMENT_AMT NUMBER;

ALTER TABLE EMPLOYEES
MODIFY AVAILABLE_AMT DEFAULT 1000;