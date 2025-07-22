GO
CREATE PROCEDURE GetBudgetsWithSpending
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT 
        b.BudgetID,
        b.UserID,
        b.CategoryID,
        c.Name AS CategoryName,
        b.Amount,
        b.Month,
        b.Year,
        ISNULL(SUM(t.Amount), 0) AS SpentAmount
    FROM Budgets b
    INNER JOIN Categories c ON b.CategoryID = c.CategoryID
    LEFT JOIN Transactions t 
        ON t.CategoryID = b.CategoryID
        AND t.UserID = @UserID
        AND MONTH(t.TransactionDate) = @Month
        AND YEAR(t.TransactionDate) = @Year
        AND c.Type = 'Expense'
    WHERE 
        b.UserID = @UserID
        AND b.Month = @Month
        AND b.Year = @Year
    GROUP BY 
        b.BudgetID,
        b.UserID,
        b.CategoryID,
        c.Name,
        b.Amount,
        b.Month,
        b.Year
END
