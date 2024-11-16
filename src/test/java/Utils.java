import groovy.json.JsonParser;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
        public static void setEnvVar(String key, String value) throws ConfigurationException {
            PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
            config.setProperty(key, value);
            config.save();
        }

        public static int generateRandomId(int min, int max){
            double randomId= Math.random()*(max-min)+min;
            int randID=(int) randomId;
            return randID;
        }

    //customer 1
    public static void addJsonList(String createdUserName, String createdUserPassword, String createdUserPhone, String createdUserEmail, String createdUserNid, String createdUserRole) throws IOException, ParseException, IOException, ParseException {

        String fileName = "./src/test/resources/info.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();

        userInfo.put("createdUserName", createdUserName);
        userInfo.put("createdUserPassword", createdUserPassword);
        userInfo.put("createdUserPhone", createdUserPhone);
        userInfo.put("createdUserEmail", createdUserEmail);
        userInfo.put("createdUserNid", createdUserNid);
        userInfo.put("createdUserRole", createdUserRole);

        jsonArray.add(userInfo);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    //customer 2
    public static void addJsonList2(String createdUserName, String createdUserPassword, String createdUserPhone, String createdUserEmail, String createdUserNid, String createdUserRole) throws IOException, ParseException, IOException, ParseException {

        String fileName = "./src/test/resources/info.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();

        userInfo.put("createdUser2Name", createdUserName);
        userInfo.put("createdUser2Password", createdUserPassword);
        userInfo.put("createdUser2Phone", createdUserPhone);
        userInfo.put("createdUser2Email", createdUserEmail);
        userInfo.put("createdUser2Nid", createdUserNid);
        userInfo.put("createdUser2Role", createdUserRole);

        jsonArray.add(userInfo);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    //Agent
    public static void addAgent(String createdAgentName, String createdAgentPassword, String createdAgentPhone, String createdUAgentEmail, String createdAgentNid, String createdAgentRole) throws IOException, ParseException {

        String fileName = "./src/test/resources/info.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userInfo = new JSONObject();

        userInfo.put("createdAgentName", createdAgentName);
        userInfo.put("createdAgentPassword", createdAgentPassword);
        userInfo.put("createdAgentPhone", createdAgentPhone);
        userInfo.put("createdAgentEmail", createdUAgentEmail);
        userInfo.put("createdAgentNid", createdAgentNid);
        userInfo.put("createdAgentRole", createdAgentRole);

        jsonArray.add(userInfo);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    //read json file
    public static JSONObject readJSONFile(String filename, int index) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filename));
        JSONArray jsonArray = (JSONArray) obj;
        int arraySize = jsonArray.size();
        int arrayIn = arraySize - index;

        JSONObject arrayObject = (JSONObject) jsonArray.get(arrayIn);

        return arrayObject;
    }

    }

