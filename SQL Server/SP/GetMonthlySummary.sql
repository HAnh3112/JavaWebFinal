GO
CREATE OR ALTER PROCEDURE GetMonthlySummary
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @StartDate DATETIME = DATEFROMPARTS(@Year, @Month, 1);
    DECLARE @EndDate DATETIME = DATEADD(MONTH, 1, @StartDate); -- exclusive

    SELECT
        COALESCE(SUM(CASE WHEN c.Type = 'Income' THEN t.Amount ELSE 0 END), 0) AS TotalIncome,
        COALESCE(SUM(CASE WHEN c.Type = 'Expense' THEN t.Amount ELSE 0 END), 0) AS TotalExpense
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE 
        t.UserID = @UserID
        AND t.TransactionDate >= @StartDate
        AND t.TransactionDate < @EndDate;
END