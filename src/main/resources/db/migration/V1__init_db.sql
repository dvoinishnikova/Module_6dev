CREATE TABLE worker (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000),
                        BIRTHDAY DATE CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900),
                        LEVEL VARCHAR(7) NOT NULL CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')),
                        SALARY INT NOT NULL CHECK (SALARY >= 100 AND SALARY <= 100000)
);

CREATE TABLE client (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000)
);

CREATE TABLE project (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         CLIENT_ID INT REFERENCES client(ID),
                         START_DATE DATE,
                         FINISH_DATE DATE
);

CREATE TABLE project_worker (
                                PROJECT_ID INT REFERENCES project(ID),
                                WORKER_ID INT REFERENCES worker(ID),
                                PRIMARY KEY (PROJECT_ID, WORKER_ID)
);