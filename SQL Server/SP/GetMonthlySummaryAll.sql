CREATE OR ALTER PROCEDURE GetMonthlySummaryAll
    @UserID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        FORMAT(MIN(t.TransactionDate), 'yyyy-MM') AS MonthValue, 
        DATENAME(MONTH, MIN(t.TransactionDate)) + ' ' + CAST(YEAR(MIN(t.TransactionDate)) AS NVARCHAR) AS MonthLabel,
        COALESCE(SUM(CASE WHEN c.Type = 'Income' THEN t.Amount ELSE 0 END), 0) AS TotalIncome,
        COALESCE(SUM(CASE WHEN c.Type = 'Expense' THEN t.Amount ELSE 0 END), 0) AS TotalExpense
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
    GROUP BY YEAR(t.TransactionDate), MONTH(t.TransactionDate)
    ORDER BY YEAR(t.TransactionDate), MONTH(t.TransactionDate);
END

exec GetMonthlySummaryAll 1