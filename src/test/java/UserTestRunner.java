import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner extends Setup {

    @Test(priority = 1,description = "Login with invalid credentials")
    public void doLoginWithInvalidCredential() throws IOException, ConfigurationException {
        UserController userController=new UserController();
        userController.doInvalidLogin("invalid@123.net","invalidPassword");
    }

    @Test ( enabled = true, priority= 2, description = "Login with valid credentials")

    public void doLogin() throws ConfigurationException, IOException {
        UserController userController = new UserController();
        userController.doLogin("admin@roadtocareer.net", "1234");

    }

    @Test(priority = 3, description= "Admin creates new user 1" )
    public void createUser() throws ConfigurationException, IOException, ParseException {

        UserController userController= new UserController();
        Faker faker=new Faker();
        UserModel model = new UserModel();

        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress().toLowerCase());
        model.setPassword("password123@");

        String phone_number="01689"+ Utils.generateRandomId(100000,999999);

        model.setPhone_number(phone_number);
        model.setNid(String.valueOf(Utils.generateRandomId(1000000000,999999999)));
        model.setRole("Customer");

        //userid stored
        JsonPath jsonPath= userController.createUser(model);
        int userID= jsonPath.get("user.id");
        Utils.setEnvVar("userID",String.valueOf(userID));

        //details json file
        String createdUserName = jsonPath.get("user.name");
        String createdUserPassword = jsonPath.get("user.password");
        String createdUserPhone = jsonPath.get("user.phone_number");
        String createdUserEmail = jsonPath.get("user.email");
        String createdUserNid = jsonPath.get("user.nid");
        String createdUserRole = jsonPath.get("user.role");
        Utils.addJsonList(createdUserName,createdUserPassword,createdUserPhone,createdUserEmail,createdUserNid,createdUserRole);

        Utils.setEnvVar("createdUserPhone",phone_number );

    }

@Test(priority = 4, description= "Admin creates another user 2" )
public void createAnotherUser() throws ConfigurationException, IOException, ParseException {

    UserController userController= new UserController();
    Faker faker=new Faker();
    UserModel model = new UserModel();

    model.setName(faker.name().fullName());
    model.setEmail(faker.internet().emailAddress().toLowerCase());
    model.setPassword("password123@");

    String phone_number="01689"+ Utils.generateRandomId(100000,999999);

    model.setPhone_number(phone_number);
    model.setNid(String.valueOf(Utils.generateRandomId(1000000000,999999999)));
    model.setRole("Customer");

    //userid stored
    JsonPath jsonPath= userController.createUser(model);
    int userID2= jsonPath.get("user.id");
    Utils.setEnvVar("userID2",String.valueOf(userID2));

    String createdUser2Name = jsonPath.get("user.name");
    String createdUser2Password = jsonPath.get("user.password");
    String createdUser2Phone = jsonPath.get("user.phone_number");
    String createdUser2Email = jsonPath.get("user.email");
    String createdUser2Nid = jsonPath.get("user.nid");
    String createdUser2Role = jsonPath.get("user.role");
    Utils.addJsonList(createdUser2Name,createdUser2Password,createdUser2Phone,createdUser2Email,createdUser2Nid,createdUser2Role);

    Utils.setEnvVar("createdUser2Phone",phone_number );

}

@Test(priority = 5, description= "Admin create a agent" )
public void createAgent() throws ConfigurationException, IOException, ParseException {

    UserController userController= new UserController();
    Faker faker=new Faker();
    UserModel model = new UserModel();

    model.setName(faker.name().fullName());
    model.setEmail(faker.internet().emailAddress().toLowerCase());
    model.setPassword("password123@");

    String phone_number="01689"+ Utils.generateRandomId(100000,999999);

    model.setPhone_number(phone_number);
    model.setNid(String.valueOf(Utils.generateRandomId(1000000000,999999999)));
    model.setRole("Agent");

    //userid stored
    JsonPath jsonPath= userController.createUser(model);
    int agentID= jsonPath.get("user.id");
    Utils.setEnvVar("agentID",String.valueOf(agentID));

    String createdAgentName = jsonPath.get("user.name");
    String createdAgentPassword = jsonPath.get("user.password");
    String createdAgentPhone = jsonPath.get("user.phone_number");
    String createdAgentEmail = jsonPath.get("user.email");
    String createdAgentNid = jsonPath.get("user.nid");
    String createdAgentRole = jsonPath.get("user.role");
    Utils.addAgent(createdAgentName,createdAgentPassword,createdAgentPhone,createdAgentEmail,createdAgentNid,createdAgentRole);

    Utils.setEnvVar("createdAgentPhone",phone_number );

}

@Test (priority= 6, description = "Admin can search")
public void searchuser() throws ConfigurationException, IOException {
    UserController userController = new UserController();
    JsonPath jsonPath = userController.searchUser(prop.getProperty("userID2"));
    String message= jsonPath.get("message");
    Assert.assertTrue(message.contains("User found"));

}
}
