package com.budgetoptimizer;

import java.util.Scanner;

public class CampaignBudgetOptimizer {

    // Method to calculate the total campaign budget based on given values
    public static double calculateTotalBudget(double X, double Y1, double Y2, double thirdPartyBudget, double fixedCosts) {
        return X + (Y1 * X) + (Y2 * thirdPartyBudget) + fixedCosts;
    }

    // Method to perform goal-seek to find optimal ad budget (X1)
    public static double goalSeek(double targetBudget, double Y1, double Y2, double fixedCosts, double[] otherAdsBudgets) {
        double tolerance = 0.01; // acceptable error margin
        double low = 0; // lower bound for X1
        double high = targetBudget; // upper bound for X1
        double X1 = 0; // initial value for X1

        while (high - low > tolerance) {
            X1 = (low + high) / 2; // midpoint for binary search

            // Calculate total ad spend X (sum of all ad budgets)
            double totalAdSpend = X1;
            for (double adBudget : otherAdsBudgets) {
                totalAdSpend += adBudget;
            }

            // Calculate third-party budgets (excluding X3) if necessary
            double thirdPartyBudget = 0;
            for (int i = 1; i < otherAdsBudgets.length; i++) {
                thirdPartyBudget += otherAdsBudgets[i];
            }
            thirdPartyBudget += X1;

            // Calculate the current total budget based on the mid value
            double currentTotalBudget = calculateTotalBudget(totalAdSpend, Y1, Y2, thirdPartyBudget, fixedCosts);

            if (currentTotalBudget > targetBudget) {
                high = X1; // budget too high, decrease upper bound
            } else {
                low = X1; // budget too low, increase lower bound
            }
        }
        return X1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input values from the user
        System.out.print("Enter total campaign budget (Z): ");
        double Z = scanner.nextDouble();

        System.out.print("Enter agency fee percentage (Y1): ");
        double Y1 = scanner.nextDouble();

        System.out.print("Enter third-party tool fee percentage (Y2): ");
        double Y2 = scanner.nextDouble();

        System.out.print("Enter fixed costs for agency hours: ");
        double HOURS = scanner.nextDouble();

        System.out.print("Enter the number of other ads: ");
        int n = scanner.nextInt();
        double[] otherAdsBudgets = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter budget for ad " + (i + 1) + ": ");
            otherAdsBudgets[i] = scanner.nextDouble();
        }

        // Call goal seek method to find optimal budget for specific ad
        double maxAdBudget = goalSeek(Z, Y1, Y2, HOURS, otherAdsBudgets);

        // Output result
        System.out.printf("Maximum budget for the specific ad: %.2f euros%n", maxAdBudget);

        scanner.close();
    }
}
