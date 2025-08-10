-- Use your database
USE PersonalFinance_DB;
GO

-- ========================================
-- 1. Main Filtered Transaction History Procedure
-- ========================================
CREATE OR ALTER PROCEDURE GetFilteredTransactionHistory
    @UserID INT,
    @TransactionType NVARCHAR(10) = NULL,  -- 'Income' or 'Expense'
    @CategoryName NVARCHAR(50) = NULL,
    @SearchTerm NVARCHAR(255) = NULL,
    @StartDate DATE = NULL,
    @EndDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        t.TransactionID,
        t.Amount,
        t.TransactionDate,
        ISNULL(t.Note, '') as Note,
        c.Name as CategoryName,
        c.Type as CategoryType,
        c.IconCode,
        c.ColorCodeHex as ColorCode
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND (@TransactionType IS NULL OR c.Type = @TransactionType)
        AND (@CategoryName IS NULL OR c.Name = @CategoryName)
        AND (@SearchTerm IS NULL OR 
             LOWER(ISNULL(t.Note, '')) LIKE '%' + LOWER(@SearchTerm) + '%' OR
             LOWER(c.Name) LIKE '%' + LOWER(@SearchTerm) + '%')
        AND (@StartDate IS NULL OR CAST(t.TransactionDate AS DATE) >= @StartDate)
        AND (@EndDate IS NULL OR CAST(t.TransactionDate AS DATE) <= @EndDate)
    ORDER BY t.TransactionDate DESC;
END;
GO

-- ========================================
-- 2. Get Transactions By Type Only
-- ========================================
CREATE OR ALTER PROCEDURE GetTransactionsByType
    @UserID INT,
    @TransactionType NVARCHAR(10)  -- 'Income' or 'Expense'
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        t.TransactionID,
        t.Amount,
        t.TransactionDate,
        ISNULL(t.Note, '') as Note,
        c.Name as CategoryName,
        c.Type as CategoryType,
        c.IconCode,
        c.ColorCodeHex as ColorCode
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND c.Type = @TransactionType
    ORDER BY t.TransactionDate DESC;
END;
GO

-- ========================================
-- 3. Search Transactions by Note/Category
-- ========================================
CREATE OR ALTER PROCEDURE SearchTransactionsByNote
    @UserID INT,
    @SearchTerm NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        t.TransactionID,
        t.Amount,
        t.TransactionDate,
        ISNULL(t.Note, '') as Note,
        c.Name as CategoryName,
        c.Type as CategoryType,
        c.IconCode,
        c.ColorCodeHex as ColorCode
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND (LOWER(ISNULL(t.Note, '')) LIKE '%' + LOWER(@SearchTerm) + '%' OR
             LOWER(c.Name) LIKE '%' + LOWER(@SearchTerm) + '%')
    ORDER BY t.TransactionDate DESC;
END;
GO

-- ========================================
-- 4. Get Transactions by Category Name
-- ========================================
CREATE OR ALTER PROCEDURE GetTransactionsByCategoryName
    @UserID INT,
    @CategoryName NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        t.TransactionID,
        t.Amount,
        t.TransactionDate,
        ISNULL(t.Note, '') as Note,
        c.Name as CategoryName,
        c.Type as CategoryType,
        c.IconCode,
        c.ColorCodeHex as ColorCode
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND c.Name = @CategoryName
    ORDER BY t.TransactionDate DESC;
END;
GO

-- ========================================
-- 5. Get Transactions by Date Range
-- ========================================
CREATE OR ALTER PROCEDURE GetTransactionsByDateRange
    @UserID INT,
    @StartDate DATE = NULL,
    @EndDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        t.TransactionID,
        t.Amount,
        t.TransactionDate,
        ISNULL(t.Note, '') as Note,
        c.Name as CategoryName,
        c.Type as CategoryType,
        c.IconCode,
        c.ColorCodeHex as ColorCode
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND (@StartDate IS NULL OR CAST(t.TransactionDate AS DATE) >= @StartDate)
        AND (@EndDate IS NULL OR CAST(t.TransactionDate AS DATE) <= @EndDate)
    ORDER BY t.TransactionDate DESC;
END;
GO

-- ========================================
-- 6. Get Filtered Transaction Summary/Stats
-- ========================================
CREATE OR ALTER PROCEDURE GetFilteredTransactionStats
    @UserID INT,
    @TransactionType NVARCHAR(10) = NULL,
    @CategoryName NVARCHAR(50) = NULL,
    @SearchTerm NVARCHAR(255) = NULL,
    @StartDate DATE = NULL,
    @EndDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        COUNT(*) as TotalTransactions,
        ISNULL(SUM(CASE WHEN c.Type = 'Income' THEN t.Amount ELSE 0 END), 0) as TotalIncome,
        ISNULL(SUM(CASE WHEN c.Type = 'Expense' THEN t.Amount ELSE 0 END), 0) as TotalExpense
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
        AND (@TransactionType IS NULL OR c.Type = @TransactionType)
        AND (@CategoryName IS NULL OR c.Name = @CategoryName)
        AND (@SearchTerm IS NULL OR 
             LOWER(ISNULL(t.Note, '')) LIKE '%' + LOWER(@SearchTerm) + '%' OR
             LOWER(c.Name) LIKE '%' + LOWER(@SearchTerm) + '%')
        AND (@StartDate IS NULL OR CAST(t.TransactionDate AS DATE) >= @StartDate)
        AND (@EndDate IS NULL OR CAST(t.TransactionDate AS DATE) <= @EndDate);
END;
GO