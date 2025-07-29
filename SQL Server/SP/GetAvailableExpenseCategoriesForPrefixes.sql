CREATE PROCEDURE GetAvailableExpenseCategoriesForPrefixes
    @UserID INT
AS
BEGIN
    SELECT c.CategoryID, c.Name
    FROM Categories c
    LEFT JOIN Prefixes p
        ON c.CategoryID = p.CategoryID
        AND p.UserID = @UserID
    WHERE c.UserID = @UserID
      AND c.Type = 'Expense'
      AND p.PrefixID IS NULL
END
