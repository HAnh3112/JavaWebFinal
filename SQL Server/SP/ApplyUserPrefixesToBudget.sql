CREATE OR ALTER PROCEDURE ApplyUserPrefixesToBudget
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    -- Step 1: Delete existing budgets for this user that match their prefixes
    DELETE b
    FROM Budgets b
    JOIN Prefixes p
        ON b.UserID = p.UserID AND b.CategoryID = p.CategoryID
    WHERE b.Month = @Month AND b.Year = @Year AND p.UserID = @UserID;

    -- Step 2: Insert that user's prefix values as new budgets
    INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year, CreatedAt)
    SELECT p.UserID, p.CategoryID, p.Amount, @Month, @Year, GETDATE()
    FROM Prefixes p
    WHERE p.UserID = @UserID;
END;
