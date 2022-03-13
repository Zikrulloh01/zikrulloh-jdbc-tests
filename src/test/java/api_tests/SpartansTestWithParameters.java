package api_tests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
public class SpartansTestWithParameters {

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://100.26.102.120:8000";
    }



    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON).
                and().pathParams("id", 5).
                when().get("api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Blythe"));
    }



    @Test
    public void test2(){


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(), 404);

        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Not Found"));
    }



    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("api/spartans/search");


        assertEquals(response.statusCode(), 200);
        assertEquals(response.getContentType(), "application/json");

        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }




    @Test
    public void mapsWithQueryParams(){
        Map<String,Object> map = new HashMap<>();
        map.put("gender", "Female");
        map.put("nameContains", "e");


        Response response = given().accept(ContentType.JSON)
                .and().queryParams(map)
                .when().get("api/spartans/search");

        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(), "application/json");

        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));

    }




}
