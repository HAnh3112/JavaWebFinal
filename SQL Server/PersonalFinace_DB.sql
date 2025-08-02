--create database PersonalFinance_DB
--drop database PersonalFinance_DB
--use PersonalFinance_DB

GO
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) UNIQUE NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    PasswordHash NVARCHAR(255) NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE()
);

GO
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT FOREIGN KEY REFERENCES Users(UserID) ON DELETE CASCADE,
    Name NVARCHAR(50) NOT NULL,
    Type NVARCHAR(10) NOT NULL CHECK (Type IN ('Income', 'Expense')),
	ColorCodeHex NVARCHAR(10) NOT NULL,
	IconCode INT NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE NONCLUSTERED INDEX IX_Categories_CategoryID_Type
ON Categories (CategoryID, Type);

GO
CREATE TABLE Prefixes (
    PrefixID INT PRIMARY KEY IDENTITY(1,1),
    CategoryID INT NOT NULL FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
    UserID INT NOT NULL FOREIGN KEY REFERENCES Users(UserID),
    Amount DECIMAL(18,2) NOT NULL,

    CONSTRAINT UQ_Prefixes_User_Category UNIQUE (UserID, CategoryID)
);


GO
CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
    Amount DECIMAL(18,2) NOT NULL,
    TransactionDate DATETIME NOT NULL,
    Note NVARCHAR(255),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE NONCLUSTERED INDEX IX_Transactions_UserCategoryDate
ON Transactions (UserID, CategoryID, TransactionDate);


GO
CREATE TABLE Budgets (
    BudgetID INT PRIMARY KEY IDENTITY(1,1),
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
    Amount DECIMAL(18,2) NOT NULL,
    Month INT NOT NULL CHECK (Month BETWEEN 1 AND 12),
    Year INT NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),

	CONSTRAINT UQ_Budgets_User_Category_Month_Year
	UNIQUE (UserID, CategoryID, Month, Year)
);

CREATE NONCLUSTERED INDEX IX_Budgets_UserMonthYear
ON Budgets (UserID, Month, Year);

