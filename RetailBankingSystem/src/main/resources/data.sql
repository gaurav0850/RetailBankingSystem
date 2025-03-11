INSERT INTO employee (username, password) VALUES ('admin', 'admin');
INSERT INTO employee (username, password) VALUES ('jane_smith', 'securePass!456');
INSERT INTO employee (username, password) VALUES ('alice_jones', 'alicePass789');
INSERT INTO employee (username, password) VALUES ('bob_brown', 'bobSecure!101');
INSERT INTO employee (username, password) VALUES ('charlie_davis', 'charliePass202');

INSERT INTO account (account_id, customer_id, initial_balance, account_type) VALUES (1, 1, 1000.00, 'SAVINGS');
INSERT INTO account (account_id, customer_id, initial_balance, account_type) VALUES (2, 2, 2500.50, 'CHECKING');
INSERT INTO account (account_id, customer_id, initial_balance, account_type) VALUES (3, 3, 1500.75, 'SAVINGS');
INSERT INTO account (account_id, customer_id, initial_balance, account_type) VALUES (4, 4, 3000.00, 'CHECKING');
INSERT INTO account (account_id, customer_id, initial_balance, account_type) VALUES (5, 5, 500.25, 'SAVINGS');

INSERT INTO customer (customer_id, first_name, last_name, email, phone) VALUES (1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890');
INSERT INTO customer (customer_id, first_name, last_name, email, phone) VALUES (2, 'Jane', 'Smith', 'jane.smith@example.com', '234-567-8901');
INSERT INTO customer (customer_id, first_name, last_name, email, phone) VALUES (3, 'Alice', 'Jones', 'alice.jones@example.com', '345-678-9012');
INSERT INTO customer (customer_id, first_name, last_name, email, phone) VALUES (4, 'Bob', 'Brown', 'bob.brown@example.com', '456-789-0123');
INSERT INTO customer (customer_id, first_name, last_name, email, phone) VALUES (5, 'Charlie', 'Davis', 'charlie.davis@example.com', '567-890-1234');

INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (1, 1, '2023-01-01', 100.00, 'Deposit');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (2, 1, '2023-01-15', -50.00, 'Withdrawal');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (3, 2, '2023-02-01', 200.00, 'Deposit');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (4, 2, '2023-02-10', -100.00, 'Withdrawal');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (5, 3, '2023-03-01', 150.00, 'Deposit');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (6, 3, '2023-03-20', -75.00, 'Withdrawal');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (7, 4, '2023-04-01', 300.00, 'Deposit');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (8, 4, '2023-04-25', -150.00, 'Withdrawal');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (9, 5, '2023-05-01', 50.00, 'Deposit');
INSERT INTO statement (statement_id, account_id, date, amount, description) VALUES (10, 5, '2023-05-15', -25.00, 'Withdrawal');