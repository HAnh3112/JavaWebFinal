GO
CREATE OR ALTER PROCEDURE GetUserTransactions
    @UserID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
		t.TransactionID,
        t.Amount,
        t.TransactionDate,
        c.Name AS CategoryName,
		c.ColorCodeHex AS ColorCode,
		c.IconCode AS IconCode,
        c.Type AS CategoryType
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
    ORDER BY t.TransactionDate DESC;
END;

GO
CREATE OR ALTER PROCEDURE GetUserRecentTransactionsByMonth
    @UserID INT,
	@Month INT,
	@Year INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP 5
        t.Amount,
        t.TransactionDate,
        c.Name AS CategoryName,
        c.Type AS CategoryType
    FROM Transactions t
    INNER JOIN Categories c ON t.CategoryID = c.CategoryID
    WHERE t.UserID = @UserID
		AND MONTH(t.TransactionDate) = @Month
		AND YEAR(t.TransactionDate) = @Year
    ORDER BY t.TransactionDate DESC;
END;
