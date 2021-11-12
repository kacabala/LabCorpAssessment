package StepDefs;

import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class MyStepdefsAPI {
    @Given("the user creates a POST request with below info and verifies the response")
    public void the_user_creates_a_POST_request_with_below_info_and_verifies_the_response(io.cucumber.datatable.DataTable dataTable) throws Throwable{
        List<Map<String,String>> maps = dataTable.asMaps();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createdAt",maps.get(0).get("createdAt"));
        jsonObject.put("employee_firstname",maps.get(0).get("employee_firstname"));
        jsonObject.put("employee_lastname",maps.get(0).get("employee_lastname"));
        jsonObject.put("employee_phonenumbe",maps.get(0).get("employee_phonenumbe"));
        jsonObject.put("ademployee_emaildress",maps.get(0).get("ademployee_emaildress"));
        jsonObject.put("citemployee_addressy",maps.get(0).get("citemployee_addressy"));
        jsonObject.put("stateemployee_dev_level",maps.get(0).get("stateemployee_dev_level"));
        jsonObject.put("employee_gender",maps.get(0).get("employee_gender"));
        jsonObject.put("employee_hire_date",maps.get(0).get("employee_hire_date"));
        jsonObject.put("employee_onleave",maps.get(0).get("employee_onleave"));
        jsonObject.put("tech_stack",new ArrayList<>());
        jsonObject.put("project",new ArrayList<>());
        System.out.println(jsonObject);


        baseURI = "https://6143a99bc5b553001717d06a.mockapi.io/testapi/v1/Users/";
        Response response = given().
                body(jsonObject.toJSONString()).
                when().post();
        //Assert.assertEquals(201,response.statusCode());
        System.out.println(response.statusCode());

        Map<String,Object> map = response.as(Map.class);

        for(Object obj: jsonObject.keySet()){
            System.out.println(jsonObject.get(obj)+" : "+map.get(obj));
        }

//        for(Object obj: jsonObject.keySet()){
//            Assert.assertEquals(jsonObject.get(obj),map.get(obj));
//        }
    }

}
