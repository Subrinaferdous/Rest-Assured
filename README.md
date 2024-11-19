## Project Title- Dmoney Rest Assured API Automation using Selenium-TestNG

An API Automation was performed by using Rest Assured, Selenium,Java, TestNG. I have automated the user creation for Customer and Agent also some transactions like- Send Money, Agent To Customer Deposit, Money Withdraw, Customer Payment, Balance check etc. Based on the given scenario, I have generated the Allure Report and given screenshot of the report.

Work Scenerio
1.Do Login by admin
2.Create 2 new customers and a agent
Give 2000 tk from System account to the newly created agent
Deposit 1500 tk to a customer from the agent account
Withdraw 500 tk by the customer to the agent
Send money 500 tk to another customer
Payment 100 tk to any merchant (e.g. 01686606905) by the recipient customer
Check balance of the recipient customer
Hints:

Keep the baseUrl, partnerKey and token into config.properties file
Keep the new customers and agents necessary info to a json array file for chaining API's (if needed to transact amount between 2 users)
Tools Used
Selenium
TestNG Framework
Java
Gradle
Allure Report
Project run gradle clean test For generating Allure Report-
allure generate allure-results --clean -o allure-report
allure serve allure-results
