-- Insert Users
INSERT INTO Users (Username, Email, PasswordHash)
VALUES 
('alice', 'alice@example.com', 'hashed_password1'),
('bob', 'bob@example.com', 'hashed_password2'),
('charlie', 'charlie@example.com', 'hashed_password3');

-- Insert Categories for Users
-- For Alice (UserID = 1)
INSERT INTO Categories (UserID, Name) VALUES 
(1, 'Groceries'),
(1, 'Entertainment');

-- For Bob (UserID = 2)
INSERT INTO Categories (UserID, Name) VALUES 
(2, 'Utilities'),
(2, 'Transport'),
(2, 'Dining');

-- For Charlie (UserID = 3)
INSERT INTO Categories (UserID, Name) VALUES 
(3, 'Healthcare'),
(3, 'Travel');

-- Insert Transactions (Income and Expense) for Each Category
-- Alice's Categories (CategoryID 1,2)
INSERT INTO Transactions (UserID, CategoryID, Amount, Type, TransactionDate, Note)
VALUES
(1, 1, 50.00, 'Expense', '2025-07-01', 'Grocery store'),
(1, 1, 30.00, 'Expense', '2025-07-05', 'Snacks'),
(1, 1, 500.00, 'Income', '2025-07-03', 'Gift'),

(1, 2, 20.00, 'Expense', '2025-07-04', 'Movie'),
(1, 2, 15.00, 'Expense', '2025-07-06', 'Music Subscription'),
(1, 2, 200.00, 'Income', '2025-07-01', 'Side gig');

-- Bob's Categories (CategoryID 3,4,5)
INSERT INTO Transactions (UserID, CategoryID, Amount, Type, TransactionDate, Note)
VALUES
(2, 3, 100.00, 'Expense', '2025-07-02', 'Electric bill'),
(2, 3, 60.00, 'Expense', '2025-07-07', 'Water bill'),
(2, 3, 1000.00, 'Income', '2025-07-01', 'Salary'),

(2, 4, 25.00, 'Expense', '2025-07-03', 'Gas'),
(2, 4, 15.00, 'Expense', '2025-07-06', 'Bus fare'),
(2, 4, 150.00, 'Income', '2025-07-01', 'Reimbursement'),

(2, 5, 40.00, 'Expense', '2025-07-05', 'Lunch'),
(2, 5, 20.00, 'Expense', '2025-07-06', 'Coffee'),
(2, 5, 300.00, 'Income', '2025-07-01', 'Freelance');

-- Charlie's Categories (CategoryID 6,7)
INSERT INTO Transactions (UserID, CategoryID, Amount, Type, TransactionDate, Note)
VALUES
(3, 6, 70.00, 'Expense', '2025-07-04', 'Medicine'),
(3, 6, 100.00, 'Expense', '2025-07-05', 'Doctor visit'),
(3, 6, 600.00, 'Income', '2025-07-02', 'Insurance payout'),

(3, 7, 300.00, 'Expense', '2025-07-01', 'Flight'),
(3, 7, 150.00, 'Expense', '2025-07-03', 'Hotel'),
(3, 7, 800.00, 'Income', '2025-07-01', 'Bonus');

-- Insert Budgets for All Categories
INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year)
VALUES
(1, 1, 200.00, 7, 2025),
(1, 2, 100.00, 7, 2025),

(2, 3, 250.00, 7, 2025),
(2, 4, 100.00, 7, 2025),
(2, 5, 150.00, 7, 2025),

(3, 6, 300.00, 7, 2025),
(3, 7, 500.00, 7, 2025);
