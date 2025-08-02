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


-- Insert Prefixes (Updated Amounts)
INSERT INTO Prefixes (CategoryID, UserID, Amount)
VALUES
(2, 1, 1500000),
(7, 3, 500000),
(8, 3, 1000000);

-- Alice's Transactions (UserID = 1)
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(1, 1, 10000000, '2025-07-01', 'Monthly salary'),
(1, 1, 10000000, '2025-06-01', 'Monthly salary'),
(1, 2, 250000, '2025-07-05', 'Groceries at supermarket'),
(1, 2, 150000, '2025-07-12', 'More groceries'),
(1, 3, 800000, '2025-07-10', 'Freelance design work');

-- Bob's Transactions (UserID = 2)
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(2, 4, 2000000, '2025-07-03', 'Stock dividends'),
(2, 4, 3000000, '2025-06-15', 'Crypto gains'),
(2, 5, 4000000, '2025-07-01', 'Monthly rent');

-- Carol's Transactions (UserID = 3)
INSERT INTO Transactions (UserID, CategoryID, Amount, TransactionDate, Note)
VALUES
(3, 6, 1500000, '2025-07-06', 'Online store sales'),
(3, 6, 1300000, '2025-06-28', 'Craft market sales'),
(3, 7, 600000, '2025-07-02', 'Dinner at sushi place'),
(3, 7, 450000, '2025-07-09', 'Lunch out'),
(3, 8, 1000000, '2025-07-04', 'Concert tickets');

-- Budgets (only Expense categories, updated Amounts)
INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year)
VALUES
(1, 2, 1000000, 7, 2025),
(2, 5, 5000000, 7, 2025),
(3, 7, 1200000, 7, 2025),
(3, 8, 1500000, 7, 2025);

