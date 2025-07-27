CREATE PROCEDURE ApplyAllPrefixesToBudget
    @Month INT,
    @Year INT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Budgets (UserID, CategoryID, Amount, Month, Year, CreatedAt)
    SELECT
        p.UserID,
        p.CategoryID,
        p.Amount,
        @Month,
        @Year,
        GETDATE()
    FROM Prefixes p
    WHERE NOT EXISTS (
        SELECT 1
        FROM Budgets b
        WHERE b.UserID = p.UserID
        AND b.CategoryID = p.CategoryID
        AND b.Month = @Month
        AND b.Year = @Year
    );
END;
