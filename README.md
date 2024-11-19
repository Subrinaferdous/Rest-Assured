## Project Title- Dmoney Rest Assured API Automation using Selenium-TestNG

An API Automation was performed by using Rest Assured, Selenium,Java, TestNG. I have automated the user creation for Customer and Agent also some transactions like- Send Money, Agent To Customer Deposit, Money Withdraw, Customer Payment, Balance check etc. Based on the given scenario, I have generated the Allure Report and given screenshot of the report.

## Work Scenerio
1. Do Login by admin
2. Create 2 new customers and a agent
3. Give 2000 tk from System account to the newly created agent
4. Deposit 1500 tk to a customer from the agent account
5. Withdraw 500 tk by the customer to the agent
6. Send money 500 tk to another customer
7. Payment 100 tk to any merchant (e.g. 01686606905) by the recipient customer
8. Check balance of the recipient customer
   
## Hints:
1. Keep the baseUrl, partnerKey and token into config.properties file
2. Keep the new customers and agents necessary info to a json array file for chaining API's (if needed to transact amount between 2 users)
   
## Tools Used
1. Selenium
2. TestNG Framework
3. Java
4. Gradle

## Allure Report
1. ``` Project run gradle clean test For generating Allure Report- ```
2. ``` allure generate allure-results --clean -o allure-report ```
3. ``` allure serve allure-results ```
