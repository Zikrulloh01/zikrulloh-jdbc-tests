package api_tests;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithPath {



    @BeforeClass
    public void beforeClass(){
        baseURI = "http://100.26.102.120:8000";
    }



    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.getContentType(), "application/json");

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        String phone = response.path("phone").toString();


        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, "3312820936");


    }


}
