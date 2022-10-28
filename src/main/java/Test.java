

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.util.*;

public class Test {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        ObjectMapper mapper = new ObjectMapper();

        File fileObj = new File("C:\\Users\\easha\\IdeaProjects\\newproject\\src\\main\\player.json");

        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            Map<String, Object> userData = mapper.readValue(fileObj, new TypeReference<Map<String, Object>>() {});

            // 1.  //Validation of that 4 foreign Players are present.
            //$.player..country
            String jsonpathPlayersCountry = "$.player[*].country";


            DocumentContext jsonContext = JsonPath.parse(fileObj);
            List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathPlayersCountry);

            List<String> country = new ArrayList<>();

            for(String s : jsonpathCreatorLocation){

                if(!s.equals("India")){
                    country.add(s);
                }
            }

            if(country.size() >= 4){
                System.out.println("pass");
            }

            System.out.println("Number of Foreign country "+country);

            //2. Validate only one wicket keeper.
            String jsonpathRole = "$.player[*].role";

            jsonContext = JsonPath.parse(fileObj);
            jsonpathCreatorLocation = jsonContext.read(jsonpathRole);

            List<String> role = new ArrayList<>();

            for(String s : jsonpathCreatorLocation){

                if(s.equals("Wicket-keeper")){
                    role.add(s);
                }
            }

            if(role.size() >= 1){
                System.out.println("pass");
            }

            System.out.println("Number of Wicket-keeper "+role);

        } catch (Exception e) {
            // show error message
            e.printStackTrace();
        }
    }
}