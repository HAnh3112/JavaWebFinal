CREATE OR ALTER PROCEDURE ApplyAllPrefixesToBudget
    @Month INT,
    @Year INT
AS
BEGIN
    -- Step 1: Delete existing budgets that match any prefix
    DELETE b
    FROM Budgets b
    JOIN Prefixes p
        ON b.UserID = p.UserID AND b.CategoryID = p.CategoryID
    WHERE b.Month = @Month AND b.Year = @Year;

    -- Step 2: Insert all prefix values as new budgets
    INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year, CreatedAt)
    SELECT p.UserID, p.CategoryID, p.Amount, @Month, @Year, GETDATE()
    FROM Prefixes p;
END;
