INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    ('John Doe', '1990-05-15', 'Senior', 5000),
    ('Jane Smith', '1995-08-21', 'Middle', 3000),
    ('Max Johnson', '2000-01-07', 'Junior', 2000),
    ('Sarah Wilson', '1992-11-30', 'Middle', 3500),
    ('Mike Davis', '1985-04-10', 'Senior', 4500),
    ('Emily Brown', '1998-07-25', 'Trainee', 1500),
    ('David Miller', '1993-03-19', 'Middle', 3200),
    ('Emma Anderson', '1988-09-02', 'Junior', 2200),
    ('James Taylor', '1991-12-05', 'Senior', 4800),
    ('Olivia Thomas', '1997-06-12', 'Junior', 2300);

INSERT INTO client (NAME)
VALUES
    ('Client A'),
    ('Client B'),
    ('Client C'),
    ('Client D'),
    ('Client E');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
    (1, '2022-01-01', '2022-06-30'),
    (1, '2022-03-15', '2022-08-31'),
    (2, '2022-05-10', '2022-10-31'),
    (3, '2022-07-01', '2022-12-31'),
    (3, '2022-09-15', '2023-03-31'),
    (4, '2022-11-01', '2023-05-31'),
    (4, '2023-01-15', '2023-07-31'),
    (5, '2023-03-01', '2023-09-30'),
    (5, '2023-05-15', '2023-12-31'),
    (5, '2023-07-01', '2024-01-31');


MERGE INTO project_worker (PROJECT_ID, WORKER_ID)
    KEY (PROJECT_ID, WORKER_ID)
    VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (3, 6),
    (3, 7),
    (4, 8),
    (4, 9),
    (5, 10),
    (6, 1),
    (6, 2),
    (7, 3),
    (7, 4),
    (8, 5),
    (8, 6),
    (9, 7),
    (9, 8),
    (10, 9),
    (10, 10);