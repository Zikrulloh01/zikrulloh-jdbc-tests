package api_tests.Json_ResponseImplementations;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.*;
public class SpartanTestWithJson {



    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }




    @Test
    public void test1(){


        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 11)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");


        //verify id and name
        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id,11);
        assertEquals(name,"Nona");


        //assign response into jsonPath
        JsonPath jsonPath = response.jsonPath();

        int id1 = jsonPath.getInt("id");
        String name1 = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        // print all values

        System.out.println("id1 = " + id1);
        System.out.println("name1 = " + name1);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


        assertEquals(id1, 11);
        assertEquals(name, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312877498L);



    }


    @Test
    public void test2(){

    }

}
