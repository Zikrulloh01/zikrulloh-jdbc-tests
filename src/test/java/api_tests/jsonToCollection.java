package api_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.testng.Assert.*;

import java.util.Map;

import  static io.restassured.RestAssured.*;

public class jsonToCollection {




    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }



    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("/api/spartans/{id}");
        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);
        String name = jsonMap.get("name").toString();
        assertEquals(name, "Meta");
    }

}
