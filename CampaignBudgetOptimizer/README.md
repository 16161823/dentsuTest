# Campaign Budget Optimizer

## Overview
This project helps optimize ad spending across various platforms for a campaign using a Goal Seek algorithm. The goal is to find the maximum budget for a specific ad while ensuring the total campaign budget remains within an approved amount.

## How to Run
1. Open the project in your IDE.
2. Run the `CampaignBudgetOptimizer` class as a Java application.
3. Follow the prompts in the console to input your data.

## Requirements
- Java JDK 11 or higher.
- Eclipse or IntelliJ IDEA.

## Author
- Michal

## Notes

### Assumptions
- This solution assumes the budget inputs are positive.
- The total campaign budget (`Z`) is assumed to be greater than the sum of fixed costs and third-party fees.

### Scalability
- The binary search used in the `goalSeek` method makes the algorithm efficient, even for larger budgets.

### Error Handling
- Basic error handling can be added to check if input values are valid.
- The program should handle edge cases, such as when the total budget is less than the fixed costs, gracefully.
- Additional checks can be incorporated to ensure input values are logical and adhere to expected constraints.
