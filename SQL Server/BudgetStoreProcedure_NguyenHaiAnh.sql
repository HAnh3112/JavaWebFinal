Go
CREATE PROCEDURE GetBudgetsWithSpending
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT 
        b.BudgetID,
        b.CategoryID,
        c.Name AS CategoryName,
        b.Amount AS BudgetedAmount,
        ISNULL(SUM(t.Amount), 0) AS SpentAmount,
        b.Month,
        b.Year
    FROM Budgets b
    INNER JOIN Categories c ON b.CategoryID = c.CategoryID
    LEFT JOIN Transactions t 
        ON b.CategoryID = t.CategoryID 
        AND t.UserID = @UserID
        AND t.Type = 'Expense'
        AND MONTH(t.TransactionDate) = @Month 
        AND YEAR(t.TransactionDate) = @Year
    WHERE 
        b.UserID = @UserID 
        AND b.Month = @Month 
        AND b.Year = @Year
    GROUP BY 
        b.BudgetID, b.CategoryID, c.Name, b.Amount, b.Month, b.Year
END

Go
CREATE PROCEDURE GetAllBudgetsWithSpending
AS
BEGIN
    SELECT 
        b.BudgetID,
        b.UserID,
        b.CategoryID,
        c.Name AS CategoryName,
        b.Amount AS BudgetedAmount,
        ISNULL(SUM(t.Amount), 0) AS SpentAmount,
        b.Month,
        b.Year,
        b.CreatedAt
    FROM Budgets b
    INNER JOIN Categories c ON b.CategoryID = c.CategoryID
    LEFT JOIN Transactions t 
        ON b.UserID = t.UserID
        AND b.CategoryID = t.CategoryID
        AND t.Type = 'Expense'
        AND MONTH(t.TransactionDate) = b.Month
        AND YEAR(t.TransactionDate) = b.Year
    GROUP BY 
        b.BudgetID, b.UserID, b.CategoryID, c.Name, b.Amount, b.Month, b.Year, b.CreatedAt
    ORDER BY b.UserID, b.Year DESC, b.Month DESC
END
