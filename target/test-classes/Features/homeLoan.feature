Feature: Home loan validations

Scenario Outline: Validate payments per month, total loan repayment and total interest
Given user is in the home page
And click on the home loan repayment calculator
When user input details "<BorrowAmount>", "<Term>", "<RepayType>" and "<HomeLoanType>"
Then Validate amounts "<MonthlyEMI>", "<TotalLoanPay>" and "<TotalInterest>"

Examples:

| BorrowAmount | Term | RepayType              | HomeLoanType                                | MonthlyEMI | TotalLoanPay | TotalInterest |
|500000        | 25   | Principal and interest | 2.39% p.a. Extra Home Loan 20% deposit      | 2216       | 664646         | 164646     |
|20000         | 2    | Interest only 3 years  | 4.14% p.a. 3 Year Fixed Rate Home Loan      | 69         | 2484           | 2484        |


Scenario Outline: Validate repayment amount after interest only periods, payments per month, Total loan and interest amounts
Given user is in the home page
And click on the home loan repayment calculator
When user input details "<BorrowAmount>", "<Term>", "<RepayType>" and "<HomeLoanType>"
Then validate monthly repay amount along with total loan amount "<MonthlyEMI>", "<TotalLoanPay>", "<TotalInterest>" and "<MonthlyRepay>"

Examples:

| BorrowAmount | Term | RepayType           | HomeLoanType                                           | MonthlyEMI | TotalLoanPay | TotalInterest | MonthlyRepay |
|10000000      | 10 | Interest only 3 years | 4.69% p.a. Standard Variable Rate Investment Home Loan | 3909       | 1305581    | 305581       | 13868 |


Scenario: Validate total repay amounts on frequency change
Given user is in the home page
And click on the home loan repayment calculator
When user enter details
| BorrowAmount   | Term | RepayType              | HomeLoanType                           | 
| 500000         | 15   | Principal and interest | 3.49% p.a. 3 Year Fixed Rate Home Loan | 

And change the Repayment frequency to "Monthly"
Then validate the repayment amounts
| MonthlyEMI | TotalLoanRepay | TotalInterest |
| 3572       | 653402         | 153402        |

And change the Repayment frequency to "Weekly"
Then validate the repayment amounts
| MonthlyEMI | TotalLoanRepay | TotalInterest |
| 825        | 652638         | 152638        |

And check for frequency message "Weekly / fortnightly amounts only apply if you’re paying by Direct Debit (set up with CommBank). For all other payment methods, you'll need to pay the monthly amount."

Scenario: Validate total repay amounts upon check/uncheck of Wealth Package
Given user is in the home page
And click on the home loan repayment calculator
When user enter details
| BorrowAmount   | Term | RepayType              | HomeLoanType                           | 
| 500000         | 15   | Principal and interest | 3.49% p.a. 3 Year Fixed Rate Home Loan |

And change the Repayment frequency to "Monthly"
And "select" the Wealth Package radio button
Then validate the repayment amounts
| MonthlyEMI | TotalLoanRepay | TotalInterest |
| 3572       | 653402         | 153402        |

And "unselect" the Wealth Package radio button
Then validate the repayment amounts
| MonthlyEMI | TotalLoanRepay | TotalInterest |
| 3609       | 676442         | 176442        |


Scenario Outline: Validate total repayment amounts upon extra payments
Given user is in the home page
And click on the home loan repayment calculator
When user input details "<BorrowAmount>", "<Term>", "<RepayType>" and "<HomeLoanType>"
And make the extra payment of "<ExtraPayAmount>"
Then Validate amounts "<MonthlyEMI>", "<TotalLoanPay>" and "<TotalInterest>"

Examples:

| BorrowAmount | Term | RepayType              | HomeLoanType                                | MonthlyEMI | TotalLoanPay | TotalInterest | ExtraPayAmount |
|6000000       | 5    | Interest only 4 years  | 4.19% p.a. 4 Year Fixed Rate Home Loan      | 21451      | 7155353      | 1155353       | 500            |



