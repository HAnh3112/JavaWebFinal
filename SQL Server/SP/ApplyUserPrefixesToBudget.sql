CREATE OR ALTER PROCEDURE ApplyUserPrefixesToBudget
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SET NOCOUNT ON;

    MERGE Budgets AS target
    USING (
        SELECT 
            p.UserID,
            p.CategoryID,
            p.Amount
        FROM Prefixes p
        WHERE p.UserID = @UserID
    ) AS source
    ON target.CategoryID = source.CategoryID
       AND target.Month = @Month
       AND target.Year = @Year

    WHEN MATCHED THEN
        UPDATE SET target.Amount = source.Amount

    WHEN NOT MATCHED THEN
        INSERT (UserID, CategoryID, Amount, Month, Year)
        VALUES (source.UserID, source.CategoryID, source.Amount, @Month, @Year);
END;
