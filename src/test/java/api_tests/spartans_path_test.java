package api_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class spartans_path_test {


    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }


    @Test
    public void getAllSpartansWithPath(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");


        assertEquals(response.statusCode(), 200);

        assertEquals(response.getHeader("Content-Type"), "application/json");

        System.out.println(response.getHeader("Content-Type"));

        assertTrue(response.getHeaders().hasHeaderWithName("Content-Type"));


        int first_id = response.path("id[0]");

        System.out.println("first_id = " + first_id);


        String first_name = response.path("name[0]");
        System.out.println("first_name = " + first_name);


        int id = response.path("id[23]");
        System.out.println("id = " + id);
        String name = response.path("name[54]");
        System.out.println("name = " + name);


        List<String> names = response.path("name");

        System.out.println("names = " + names);

        System.out.println("names.size() = " + names.size());
        String name1 = response.path("name[96]");
        System.out.println("name1 = " + name1);


        System.out.println("==========================================================================");


        List<Object> phones = response.path("phone");

        for (Object phone : phones) {
            System.out.println(phone);
        }



    }
}
