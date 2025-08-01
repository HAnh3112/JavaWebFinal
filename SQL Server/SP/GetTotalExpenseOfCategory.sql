GO
CREATE OR ALTER PROCEDURE GetTotalExpenseOfCategory
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @StartDate DATE = DATEFROMPARTS(@Year, @Month, 1);
    DECLARE @EndDate DATE = DATEADD(MONTH, 1, @StartDate); -- exclusive

    SELECT 
        c.CategoryID,
        c.Name AS CategoryName,
        SUM(t.Amount) AS TotalSpent
    FROM Transactions t
    JOIN Categories c 
        ON t.CategoryID = c.CategoryID
    WHERE 
        t.UserID = @UserID
        AND c.UserID = @UserID
        AND c.Type = 'Expense'
        AND t.TransactionDate >= @StartDate AND t.TransactionDate < @EndDate
    GROUP BY 
        c.CategoryID, c.Name, c.ColorCodeHex, c.IconCode
    ORDER BY 
        TotalSpent DESC;
END
