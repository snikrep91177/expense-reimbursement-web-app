

------------------------------ERS_USER_ROLES---------------------------------------

--Create look-up table that will hold user roles
CREATE TABLE ERS_USER_ROLES(
    user_role_id NUMBER,
    user_role VARCHAR(20),
    CONSTRAINT user_roles_pk PRIMARY KEY (user_role_id)
);

DROP TABLE ERS_USER_ROLES;

--Sequence that starts at 1 and increments user_role_id by 1 for each new entry 
CREATE SEQUENCE user_roles_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE user_roles_seq;    
    
--Procedure which uses user_roles_seq to update user_role_id for each new entry 
CREATE OR REPLACE PROCEDURE insert_roles_null_id(u_role IN VARCHAR2)
IS
BEGIN
    INSERT INTO ERS_USER_ROLES VALUES(user_roles_seq.NEXTVAL, u_role);
    COMMIT;
END;
/


------------------------------ERS_REIMBURSEMENT_TYPE-------------------------------

--Create look-up table that will hold reimbursement types
CREATE TABLE ERS_REIMBURSEMENT_TYPE(
    reimb_type_id NUMBER,
    reimb_type VARCHAR(20),
    CONSTRAINT reimb_type_pk PRIMARY KEY (reimb_type_id)
);

DROP TABLE ERS_REIMBURSEMENT_TYPE;

--Sequence that starts at 1 and increments reimb_type_id by 1 for each new entry 
CREATE SEQUENCE reimb_type_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE reimb_type_seq;    
    
--Procedure which uses reimb_type_seq to update reimb_type_id for each new entry 
CREATE OR REPLACE PROCEDURE insert_reimb_type_null_id(r_type IN VARCHAR2)
IS
BEGIN
    INSERT INTO ERS_REIMBURSEMENT_TYPE VALUES(reimb_type_seq.NEXTVAL, r_type);
    COMMIT;
END;
/


------------------------------ERS_REIMBURSEMENT_STATUS-------------------------------------

--Create look-up table that will hold reimbursement status
CREATE TABLE ERS_REIMBURSEMENT_STATUS(
    reimb_status_id NUMBER,
    reimb_status VARCHAR(20),
    CONSTRAINT reimb_status_pk PRIMARY KEY (reimb_status_id)
);    

DROP TABLE ERS_REIMBURSEMENT_STATUS;

--Sequence that starts at 1 and increments reimb_status_id by 1 for each new entry 
CREATE SEQUENCE reimb_status_seq
    START WITH 1
    INCREMENT BY 1;
    
DROP SEQUENCE reimb_status_seq;    
    
--Procedure which uses reimb_status_seq to update reimb_status_id for each new entry 
CREATE OR REPLACE PROCEDURE insert_reimb_status_null_id(r_status IN VARCHAR2)
IS
BEGIN
    INSERT INTO ERS_REIMBURSEMENT_STATUS VALUES(reimb_status_seq.NEXTVAL, r_status);
    COMMIT;
END;
/


------------------------------------ERS_USERS-------------------------------------------

--Create users table for ERS system
CREATE TABLE ERS_USERS(
    user_id NUMBER,
    username VARCHAR2(50) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    first_name VARCHAR2(100) NOT NULL,
    last_name VARCHAR2(100)NOT NULL,
    email VARCHAR2(150) NOT NULL,
    user_role_id NUMBER NOT NULL,
    CONSTRAINT ers_users_pk PRIMARY KEY (user_id),
    CONSTRAINT user_roles_fk
        FOREIGN KEY (user_role_id)
        REFERENCES ERS_USER_ROLES(user_role_id)
        ON DELETE CASCADE,
    CONSTRAINT ers_users_unique 
        UNIQUE (username, email) 
);

--drop users table
DROP TABLE ERS_USERS;


--Sequence that starts at 1 and increments ers_user by 1 for each new entry 
CREATE SEQUENCE ers_users_seq
    START WITH 1
    INCREMENT BY 1;
    
--drop ers_users_seq sequence
DROP SEQUENCE ers_users_seq;

--Procedure which uses ers_user_seq to update ers_user_id for each new entry 
CREATE OR REPLACE PROCEDURE insert_user(u_username IN VARCHAR2, u_password IN VARCHAR2,
    u_first_name IN VARCHAR2, u_last_name IN VARCHAR2, u_email IN VARCHAR2, u_role_id IN NUMBER)
IS
BEGIN
    INSERT INTO ERS_USERS VALUES(
        null, u_username, u_password, u_first_name, u_last_name, u_email, u_role_id);
        COMMIT;
END;
/


----------------------------------------ERS_REIMBURSEMENTS--------------------------------------------


--Create reimbursment table for ers system
CREATE TABLE ERS_REIMBURSEMENT(
    reimb_id NUMBER,
    amount NUMBER NOT NULL,
    submitted TIMESTAMP,
    resolved TIMESTAMP,
    description VARCHAR2(255) NOT NULL,
    receipt BLOB,
    author NUMBER NOT NULL,
    resolver NUMBER,
    reimb_status_id NUMBER NOT NULL,
    reimb_type_id NUMBER NOT NULL,
    CONSTRAINT ers_reimbursement_pk PRIMARY KEY(reimb_id),
    CONSTRAINT ers_users_auth_fk 
        FOREIGN KEY (author) 
        REFERENCES ERS_USERS(user_id)
        ON DELETE CASCADE,
    CONSTRAINT ers_users_reslvr_fk
        FOREIGN KEY (resolver)
        REFERENCES ERS_USERS(user_id)
        ON DELETE CASCADE,
    CONSTRAINT ers_reimb_status_fk
        FOREIGN KEY (reimb_status_id)
        REFERENCES ERS_REIMBURSEMENT_STATUS(reimb_status_id)
        ON DELETE CASCADE,
    CONSTRAINT ers_reimb_type_fk
        FOREIGN KEY (reimb_type_id)
        REFERENCES ERS_REIMBURSEMENT_TYPE(reimb_type_id)
        ON DELETE CASCADE
);

DROP TABLE ERS_REIMBURSEMENT;


--Sequence that starts at 1 and increments ers_user by 1 for each new entry 
CREATE SEQUENCE ers_reimb_seq
    START WITH 1
    INCREMENT BY 1;

DROP SEQUENCE ers_reimb_seq;    

--Procedure which uses ers_reimb_seq to update reimb_id for each new entry 
CREATE OR REPLACE PROCEDURE insert_reimb_null_id(r_amount IN NUMBER, r_description IN VARCHAR2,
    r_author IN NUMBER, r_type_id IN NUMBER)
IS
BEGIN
    INSERT INTO ERS_REIMBURSEMENT VALUES(
        ers_reimb_seq.NEXTVAL, r_amount, CURRENT_TIMESTAMP, null, r_description, null,
        r_author, null, 1, r_type_id);
        COMMIT;
END;
/


--------------------------------------PASSWORD HASHING---------------------------------------------


--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD  
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

----TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR ID AND HASHES BLANK PASSWORD
CREATE OR REPLACE TRIGGER USER_INSERT
BEFORE INSERT
ON ERS_USERS
FOR EACH ROW
BEGIN

--INCREASE THE SEQUENCE 
  IF :NEW.user_id IS NULL THEN
    SELECT ers_users_seq.NEXTVAL INTO :NEW.user_id FROM DUAL;
  END IF; 
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  SELECT GET_USER_HASH(:NEW.username,:NEW.password) INTO :NEW.password FROM DUAL;
END;
/


----------------------------------MANAGER VIEW OF REIMBURSEMENTS------------------------------


--"Employee", "Amount", "Submit Date", "Resolve Date",
--"Description", "Manager", "Status", "Type"
CREATE OR REPLACE VIEW manager_reimb_view AS 
SELECT reimb_id, first_name || ' ' || last_name employee,
        amount, submitted, resolved, description, 
        resolver, reimb_status_id, reimb_type_id
FROM ERS_USERS
JOIN ERS_REIMBURSEMENT
ON (user_id = author);


------------------------------------MANAGER VIEW OF REIMBURSEMENTS IMPROVED--------------------------

CREATE OR REPLACE VIEW manager_reimb_view AS 
SELECT reimb_id, first_name || ' ' || last_name employee,
        amount, submitted, resolved, description, 
        resolver, reimb_status status, reimb_type type
FROM ERS_USERS
JOIN ERS_REIMBURSEMENT
    ON (user_id = author)
JOIN ERS_REIMBURSEMENT_STATUS
    USING(reimb_status_id)
JOIN ERS_REIMBURSEMENT_TYPE
    USING(reimb_type_id);
    

------------------------------------MANAGER VIEW OF REIMBURSEMENTS IMPROVED--------------------------


CREATE OR REPLACE VIEW manager_reimb_view AS 
SELECT reimb_id, first_name || ' ' || last_name employee,
        amount, submitted, resolved, description, 
        manager resolver, reimb_status status, reimb_type type
FROM ERS_USERS
JOIN ERS_REIMBURSEMENT
    ON (user_id = author)
JOIN ERS_REIMBURSEMENT_STATUS
    USING(reimb_status_id)
JOIN ERS_REIMBURSEMENT_TYPE
    USING(reimb_type_id)
LEFT JOIN manager_name
    USING(resolver);
    
    
--------------------------------------EMPLOYEE REIMBURSEMENT VIEW-------------------------------------


CREATE OR REPLACE VIEW employee_reimb_view AS 
SELECT user_id id, amount, submitted, resolved, description, 
       manager resolver, reimb_status status, reimb_type type
FROM ERS_REIMBURSEMENT
JOIN ERS_USERS
    ON(user_id=author)
JOIN ERS_REIMBURSEMENT_STATUS
    USING(reimb_status_id)
JOIN ERS_REIMBURSEMENT_TYPE
    USING(reimb_type_id)
LEFT JOIN manager_name
    USING(resolver);


---------------------------------------MANAGER NAME VIEW-----------------------------------------


CREATE OR REPLACE VIEW manager_name AS
SELECT user_id resolver, first_name || ' ' || last_name manager
FROM ERS_USERS
WHERE (user_role_id = 2);


--------------------------------------------INSERTS---------------------------------------------------


UPDATE ERS_REIMBURSEMENT SET reimb_status_id=2, resolver=24, resolved=CURRENT_TIMESTAMP WHERE reimb_id=45;


--Insert entries into look_up tables
BEGIN
    insert_roles_null_id('employee');
    insert_roles_null_id('manager');
    insert_reimb_type_null_id('lodging');
    insert_reimb_type_null_id('travel');
    insert_reimb_type_null_id('food');
    insert_reimb_type_null_id('other');
    insert_reimb_status_null_id('pending');
    insert_reimb_status_null_id('approved');
    insert_reimb_status_null_id('denied');
END;
/



--Verify results of sequence/null-id procedures
BEGIN
    insert_reimb_null_id(50.00, CURRENT_TIMESTAMP, null, 'Business Dinner', null, 1, null, 1, 3);
    insert_reimb_null_id(500.00, CURRENT_TIMESTAMP, null, 'Three night stay in Tampa, Fl', null, 2, null, 1, 1);
END;
/  


--Verify results of sequence/null-id procedures

BEGIN
    insert_user('mgperkins1', 'surfing1', 'Michael', 'Perkins', 'mgperkins1@yahoo.com', 1);
    insert_user('amailina', 'gamalito1', 'Amail', 'Perkins', 'amailamail@yahoo.com', 1);
    insert_user('emp1', 'pass', 'Kevin', 'Perkins', 'kevin@yahoo.com', 2);
END;
/


------------------------------------UPDATES-SELECTS---------------------------------------------------

    
--Verify results of sequence/null-id procedures
SELECT * FROM ERS_USER_ROLES;
SELECT * FROM ERS_REIMBURSEMENT_STATUS;
SELECT * FROM ERS_REIMBURSEMENT_TYPE;
SELECT * FROM ERS_USERS;
SELECT * FROM ERS_REIMBURSEMENT;
SELECT * FROM manager_reimb_view;
--SELECT * FROM manager_name;
--SELECT * FROM manager_reimb_view WHERE status='denied';
--SELECT * FROM employee_reimb_view WHERE ID=21;