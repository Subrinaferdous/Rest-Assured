import Model.TransactionModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TransactionController  extends Setup {
    public TransactionController() throws IOException {
        initConfig();
    }

    public JsonPath doTransactionFromSystemToAgent() throws InterruptedException {

        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");
//      model.UserModel model=new model.UserModel();

        TransactionModel systemToAgentTransaction  = new TransactionModel("SYSTEM",prop.getProperty("createdAgentPhone"), 2000 );
        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .body(systemToAgentTransaction)
                .when()
                .post("/transaction/deposit");

        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath agentToCustomerTransaction(String agentPhoneNumber, String customerPhoneNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");

        TransactionModel agentTransactionModel   = new TransactionModel(agentPhoneNumber,customerPhoneNumber, amount );
        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .body(agentTransactionModel )
                .when()
                .post("/transaction/deposit");

        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath moneyWithdrawalByCustomer(String customerPhoneNumber, String agentPhoneNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");

        TransactionModel withdrawal = new TransactionModel(customerPhoneNumber,agentPhoneNumber, amount );
        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .body(withdrawal)
                .when()
                .post("/transaction/withdraw");

        System.out.println(res.asString());
        return res.jsonPath();
    }


    public JsonPath sendMoneyToOtherCustomer(String fromCustomer1Number, String toCustomer2Number, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");

        TransactionModel sendMoney = new TransactionModel(fromCustomer1Number,toCustomer2Number, amount );
        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .body(sendMoney)
                .when()
                .post("/transaction/sendmoney");

        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath paymentToMerchantNumber(String fromNumber, String toNumber, int amount) throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");

        TransactionModel payment = new TransactionModel(fromNumber,toNumber, amount );
        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .body(payment)
                .when()
                .post("/transaction/payment");

        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath checkCustomerBalance() throws InterruptedException {
        Thread.sleep(5000);
        RestAssured.baseURI=prop.getProperty("baseUrl");
        String BearerToken= (String) prop.get("token");

        Response res =given()
                .contentType("application/json")
                .header("Authorization","Bearer "+BearerToken)
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when()
                .get("/transaction/balance/"+ prop.getProperty("createdUser2Phone"));

        System.out.println(res.asString());
        return res.jsonPath();
    }
}



////
//import Model.TransactionModel;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//
//import java.io.IOException;
//
//import static io.restassured.RestAssured.given;
//
//public class TransactionController {
//
//    private System prop;
//
//    public TransactionController() throws IOException {
//        initConfig();
//    }
//
//    public JsonPath doTransactionFromSystemToAgent() throws InterruptedException {
//
//        RestAssured.baseURI= Setup.prop.getProperty("baseUrl");
//        String BearerToken= (String) Setup.prop.get("token");
//
//        TransactionModel systemToAgentTransaction  = new TransactionModel("SYSTEM",prop.getProperty("createdAgentPhone"), 2000 );
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
//                .body(systemToAgentTransaction)
//                .when()
//                .post("/transaction/deposit");
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//
//    public JsonPath agentToCustomerTransaction(String agentPhoneNumber, String customerPhoneNumber, int amount) throws InterruptedException {
//        Thread.sleep(5000);
//        RestAssured.baseURI= Setup.prop.getProperty("baseUrl");
//        String BearerToken= (String) Setup.prop.get("token");
//
//        TransactionModel agentTransactionModel   = new TransactionModel(agentPhoneNumber,customerPhoneNumber, amount );
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY", Setup.prop.getProperty("partnerKey"))
//                .body(agentTransactionModel )
//                .when()
//                .post("/transaction/deposit");
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//
//    public JsonPath moneyWithdrawalByCustomer(String customerPhoneNumber, String agentPhoneNumber, int amount) throws InterruptedException {
//        Thread.sleep(5000);
//        RestAssured.baseURI= Setup.prop.getProperty("baseUrl");
//        String BearerToken= (String) Setup.prop.get("token");
//
//        TransactionModel withdrawal = new TransactionModel(customerPhoneNumber,agentPhoneNumber, amount );
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY", Setup.prop.getProperty("partnerKey"))
//                .body(withdrawal)
//                .when()
//                .post("/transaction/withdraw");
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//    public JsonPath sendMoneyToOtherCustomer(String fromCustomer1Number, String toCustomer2Number, int amount) throws InterruptedException {
//        Thread.sleep(5000);
//        RestAssured.baseURI=prop.getProperty("baseUrl");
//        String BearerToken= (String) prop.get("token");
//
//        TransactionModel sendMoney = new TransactionModel(fromCustomer1Number,toCustomer2Number, amount );
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
//                .body(sendMoney)
//                .when()
//                .post("/transaction/sendmoney");
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//    public JsonPath paymentToMerchantNumber(String fromNumber, String toNumber, int amount) throws InterruptedException {
//        Thread.sleep(5000);
//        RestAssured.baseURI=prop.getProperty("baseUrl");
//        String BearerToken= (String) prop.get("token");
//
//        TransactionModel payment = new TransactionModel(fromNumber,toNumber, amount );
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
//                .body(payment)
//                .when()
//                .post("/transaction/payment");
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//    public JsonPath checkCustomerBalance() throws InterruptedException {
//        Thread.sleep(5000);
//        RestAssured.baseURI=prop.getProperty("baseUrl");
//        String BearerToken= (String) prop.get("token");
//
//        Response res =given()
//                .contentType("application/json")
//                .header("Authorization","Bearer "+BearerToken)
//                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
//                .when()
//                .get("/transaction/balance/"+ prop.getProperty("createdUser2Phone"));
//
//        System.out.println(res.asString());
//        return res.jsonPath();
//    }
//}
