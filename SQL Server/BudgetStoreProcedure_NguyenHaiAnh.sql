GO
CREATE OR ALTER PROCEDURE GetBudgetsWithSpending
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    -- Pre-filtered transactions
	;WITH FilteredTransactions AS (
		SELECT *
		FROM Transactions
		WHERE 
			UserID = @UserID AND
			TransactionDate >= DATEFROMPARTS(@Year, @Month, 1) AND
			TransactionDate < DATEADD(MONTH, 1, DATEFROMPARTS(@Year, @Month, 1))
	)
    SELECT 
        b.BudgetID,
        b.UserID,
        b.CategoryID,
        c.Name AS CategoryName,
        c.IconCode AS IconCode,
        b.Amount,
        b.Month,
        b.Year,
        ISNULL(SUM(t.Amount), 0) AS SpentAmount
    FROM Budgets b
    INNER JOIN Categories c ON b.CategoryID = c.CategoryID AND c.Type = 'Expense'
    LEFT JOIN FilteredTransactions t 
        ON t.CategoryID = b.CategoryID
    WHERE 
        b.UserID = @UserID AND
        b.Month = @Month AND
        b.Year = @Year
    GROUP BY 
        b.BudgetID,
        b.UserID,
        b.CategoryID,
        c.Name,
        c.IconCode,
        b.Amount,
        b.Month,
        b.Year
END


EXEC GetBudgetsWithSpending 3,7,2025