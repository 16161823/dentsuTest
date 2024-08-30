package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.budgetoptimizer.CampaignBudgetOptimizer;

public class CampaignBudgetOptimizerTest {

    @Test
    public void testGoalSeek_ValidInputs() {
        double Z = 1000; // Total budget
        double Y1 = 0.10; // Agency fee percentage (10%)
        double Y2 = 0.05; // Third-party tool fee percentage (5%)
        double HOURS = 100; // Fixed cost for agency hours
        double[] otherAdsBudgets = {200, 150, 100}; // Budgets for other ads

        // Call the goalSeek method
        double result = CampaignBudgetOptimizer.goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);

        // Expected result should be the maximum budget for the specific ad that fits within the total budget Z
        assertEquals(407.4074, result, 0.01, "The maximum budget for the specific ad should be approximately 407.41 euros");
    }

    @Test
    public void testGoalSeek_NegativeBudget() {
        double Z = -1000; // Negative total budget
        double Y1 = 0.10; // Agency fee percentage (10%)
        double Y2 = 0.05; // Third-party tool fee percentage (5%)
        double HOURS = 100; // Fixed cost for agency hours
        double[] otherAdsBudgets = {200, 150, 100}; // Budgets for other ads

        // Assert that the method throws an IllegalArgumentException for negative budgets
        assertThrows(IllegalArgumentException.class, () -> {
            CampaignBudgetOptimizer.goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);
        });
    }

    @Test
    public void testGoalSeek_ZeroBudget() {
        double Z = 0; // Zero total budget
        double Y1 = 0.10; // Agency fee percentage (10%)
        double Y2 = 0.05; // Third-party tool fee percentage (5%)
        double HOURS = 0; // Zero fixed costs for agency hours
        double[] otherAdsBudgets = {}; // No other ads

        // Call the goalSeek method
        double result = CampaignBudgetOptimizer.goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);

        // Expected result should be zero since there's no budget to allocate
        assertEquals(0, result, 0.01, "The maximum budget for the specific ad should be zero");
    }

    @Test
    public void testGoalSeek_BudgetLessThanFixedCosts() {
        double Z = 50; // Total budget less than fixed costs
        double Y1 = 0.10; // Agency fee percentage (10%)
        double Y2 = 0.05; // Third-party tool fee percentage (5%)
        double HOURS = 100; // Fixed cost for agency hours
        double[] otherAdsBudgets = {20}; // Budgets for other ads

        // Assert that the method throws an IllegalArgumentException for budget less than fixed costs
        assertThrows(IllegalArgumentException.class, () -> {
            CampaignBudgetOptimizer.goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);
        });
    }

    @Test
    public void testGoalSeek_HighFeePercentages() {
        double Z = 1000; // Total budget
        double Y1 = 0.90; // High agency fee percentage (90%)
        double Y2 = 0.80; // High third-party tool fee percentage (80%)
        double HOURS = 100; // Fixed cost for agency hours
        double[] otherAdsBudgets = {100, 150}; // Budgets for other ads

        // Call the goalSeek method
        double result = CampaignBudgetOptimizer.goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);

        // Even with high fees, the result should be a reasonable budget
        assertEquals(156.25, result, 0.01, "The maximum budget for the specific ad should be approximately 156.25 euros");
    }
}