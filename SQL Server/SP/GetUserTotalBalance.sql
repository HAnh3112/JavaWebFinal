CREATE OR ALTER PROCEDURE GetUserTotalBalance
    @UserID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        COALESCE(SUM(CASE WHEN c.Type = 'Income' THEN t.Amount ELSE 0 END), 0)
        - COALESCE(SUM(CASE WHEN c.Type = 'Expense' THEN t.Amount ELSE 0 END), 0)
        AS TotalBalance
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID;
END

exec GetUserTotalBalance 1