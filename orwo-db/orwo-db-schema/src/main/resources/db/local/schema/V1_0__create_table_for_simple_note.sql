--
-- Create table to store notes
--

CREATE TABLE T_NOTE (
  ID DECIMAL(18,0),
  TIMESTAMP TIMESTAMP,
  CONTENT VARCHAR(5000),
  PRIMARY KEY (ID)
);

CREATE SEQUENCE SEQ_ID_NOTE START WITH 1000000 INCREMENT BY 10;
