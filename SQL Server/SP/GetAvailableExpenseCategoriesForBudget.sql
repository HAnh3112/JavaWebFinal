CREATE PROCEDURE GetAvailableExpenseCategoriesForBudget
    @UserID INT,
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT c.CategoryID, c.Name
    FROM Categories c
    LEFT JOIN Budgets b
        ON c.CategoryID = b.CategoryID
        AND b.UserID = @UserID
        AND b.Month = @Month
        AND b.Year = @Year
    WHERE c.UserID = @UserID
      AND c.Type = 'Expense'
      AND b.BudgetID IS NULL
END
