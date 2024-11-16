import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TransactionTestRunner extends Setup {

    TransactionController transactionController;
    JsonPath jsonPath;

    public TransactionTestRunner() throws IOException {
    }

    //Successful Transaction from System Account To Agent Account: Deposit 2000tk from system to agent
    @Test(priority = 1,enabled = true, description = "Successful Transaction from System Account To Agent Account 2000tk")
    public void systemToAgentTransaction () throws IOException, InterruptedException {

        transactionController=new TransactionController();
        jsonPath = transactionController.doTransactionFromSystemToAgent();

        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Deposit successful"));

    }

    @Test(priority = 2,enabled = true, description = "Successful Transaction (Deposit) from Agent to Customer1: 1500tk ")
    public void agentToCustomerTransaction () throws InterruptedException, IOException {
        Thread.sleep(3000);

        String agentPhoneNumber = prop.getProperty("createdAgentPhone");
        String customerPhoneNumber = prop.getProperty("createdUserPhone");
        System.out.println("Agent Number: "+agentPhoneNumber);
        transactionController=new TransactionController();
        jsonPath =  transactionController.agentToCustomerTransaction(agentPhoneNumber, customerPhoneNumber, 1500);
        //jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Deposit successful"));
    }

    //Successful Money Withdrawal By Customer 1: Withdraw 500tk by the customer 1 to the agent
    @Test(priority = 3,enabled = true, description = "Successful Money Withdrawal By Customer 1 to Agent 500tk")
    public void moneyWithdrawalByCustomer () throws InterruptedException, IOException {
        Thread.sleep(3000);
        String customerPhoneNumber = prop.getProperty("createdUserPhone");
        String agentPhoneNumber = prop.getProperty("createdAgentPhone");
        int amount = 500;

        transactionController=new TransactionController();
        jsonPath =  transactionController.moneyWithdrawalByCustomer(customerPhoneNumber, agentPhoneNumber, amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Withdraw successful"));
    }

    //Successful Send money from Customer 1 to Valid Customer 2 Number
    @Test(priority = 4,enabled = true, description = "Successful Send money from Customer 1 to Customer 2 Number : 500tk")
    public void sendMoney() throws InterruptedException, IOException {
        Thread.sleep(3000);
        String fromCustomer1Number = prop.getProperty("createdUserPhone");
        String toCustomer2Number = prop.getProperty("createdUser2Phone");
        int amount = 500;

        transactionController=new TransactionController();
        jsonPath =  transactionController.sendMoneyToOtherCustomer(fromCustomer1Number, toCustomer2Number, amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Send money successful"));
    }

    //Successful Payment To a Valid Merchant Number from customer 2
    @Test(priority = 5,enabled = true, description = "Successful Payment To a valid Merchant Number from customer 2:100tk")
    public void paymentToValidMerchant () throws IOException, InterruptedException {
        String fromNumber = prop.getProperty("createdUser2Phone");
        String toNumber = "01686606905";
        int amount = 100;

        transactionController=new TransactionController();
        jsonPath =  transactionController.paymentToMerchantNumber(fromNumber,toNumber,amount);
        String message = jsonPath.get("message");
        System.out.println(message);
        Assert.assertTrue(message.contains("Payment successful"));
    }

    //Successful checking of customer 2 balance with correct method
    @Test(priority = 6,enabled = true, description = "Successful checking of customer 2 balance")
    public void checkCustomerBalance () throws IOException, InterruptedException {

        transactionController=new TransactionController();
        jsonPath =  transactionController.checkCustomerBalance();
        String message = jsonPath.get("message");
        Assert.assertEquals(message, "User balance");
    }

}
