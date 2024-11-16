import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController extends Setup {

    public UserController() throws IOException {
        initConfig();
    }

    String token,message ;

    public void doInvalidLogin(String email, String password) throws ConfigurationException {
        RestAssured.baseURI=prop.getProperty("baseUrl");
        //User Model Use-->
        UserModel model=new UserModel();
        model.setEmail(email);
        model.setPassword(password);

        Response res=given()
                .contentType("application/json")
                .body(model)
                .when()
                .post("/user/login")
                .then()
                .assertThat().statusCode(404).extract().response();

        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        token=jsonPath.get("token");
        message=jsonPath.get("message");
        System.out.println(message);

        Utils.setEnvVar("token", token);
        Assert.assertTrue(message.contains("User not found"));

    }

    public void doLogin(String email, String password) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");

        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);

        Response res = given().
                contentType("application/json").
                body(model).
                post("/user/login");
                //then().
                //assertThat().statusCode(200).extract().response();
        ;

        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);

    }

    public JsonPath searchUser(String userId) throws IOException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String BearerToken = (String) prop.get("token");

        Response res = given().
                contentType("application/json").
                header("Authorization", "Bearer " + BearerToken).
                when().
                get("/user/search/id/" + userId);
        System.out.println(res.asString());
        return res.jsonPath();
    }


    public JsonPath createUser(UserModel model) throws ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        String BearerToken = (String) prop.get("token");

        Response res = given().
                contentType("application/json").
                header("Authorization", "Bearer " + BearerToken).
                header("X-AUTH-SECRET-KEY", prop.get("partnerKey")).
                body(model).
                when().post("/user/create").
                then().
                assertThat().statusCode(201).extract().response();


        System.out.println(res.asString());
        return res.jsonPath();
    }
}


