-- Insert Users
INSERT INTO Users (Username, Email, PasswordHash)
VALUES 
('alice', 'alice@example.com', 'hashed_pw1'),
('bob', 'bob@example.com', 'hashed_pw2'),
('carol', 'carol@example.com', 'hashed_pw3');

-- Insert Categories for each User
-- Alice: 3 Categories
INSERT INTO Categories (UserID, Name, Type, ColorCodeHex, IconCode)
VALUES
(1, 'Salary', 'Income', '#FF6B6B', 57522),
(1, 'Groceries', 'Expense', '#4ECDC4', 58778),
(1, 'Freelance', 'Income', '#45B7D1', 57522);

-- Bob: 2 Categories
INSERT INTO Categories (UserID, Name, Type, ColorCodeHex, IconCode)
VALUES
(2, 'Investments', 'Income', '#96CEB4', 57522),
(2, 'Rent', 'Expense', '#FFEAA7', 58136);

-- Carol: 3 Categories
INSERT INTO Categories (UserID, Name, Type, ColorCodeHex, IconCode)
VALUES
(3, 'Side Hustle', 'Income', '#DDA0DD', 57522),
(3, 'Dining Out', 'Expense', '#FFB6C1', 57946),
(3, 'Entertainment', 'Expense', '#98D8C8', 58856);

-- Insert Transactions (match category type)
-- Alice's Transactions
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(1, 1, 5000.00, '2025-07-01', 'Monthly salary'),
(1, 1, 5000.00, '2025-06-01', 'Monthly salary'),
(1, 2, 150.75, '2025-07-05', 'Groceries at supermarket'),
(1, 2, 120.00, '2025-07-12', 'More groceries'),
(1, 3, 800.00, '2025-07-10', 'Freelance design work');

-- Bob's Transactions
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(2, 4, 200.00, '2025-07-03', 'Stock dividends'),
(2, 4, 220.00, '2025-06-15', 'Crypto gains'),
(2, 5, 900.00, '2025-07-01', 'Monthly rent');

-- Carol's Transactions
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(3, 6, 700.00, '2025-07-06', 'Online store sales'),
(3, 6, 650.00, '2025-06-28', 'Craft market sales'),
(3, 7, 60.00, '2025-07-02', 'Dinner at sushi place'),
(3, 7, 45.50, '2025-07-09', 'Lunch out'),
(3, 8, 100.00, '2025-07-04', 'Concert tickets');

-- Insert Budgets for each Expense Category
-- Alice's Expense Category: CategoryID = 2
INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year)
VALUES
(1, 2, 400.00, 7, 2025);

-- Bob's Expense Category: CategoryID = 5
INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year)
VALUES
(2, 5, 1000.00, 7, 2025);

-- Carol's Expense Categories: CategoryID = 7, 8
INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year)
VALUES
(3, 7, 200.00, 7, 2025),
(3, 8, 300.00, 7, 2025);
