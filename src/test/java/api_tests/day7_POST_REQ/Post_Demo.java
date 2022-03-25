package api_tests.day7_POST_REQ;

import api_tests.day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Post_Demo {




    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }


/*
* {
    "success": "A Spartan is Born!",
    "data": {
        "id": 126,
        "name": "AleX",
        "gender": "Male",
        "phone": 9989735974413
    }
}*/
    @Test
    public void postSpartan(){
        String jsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"AleX\",\n" +
                "  \"phone\": 9989735974413\n" +
                "  }";
        Response postResponse = given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        postResponse.prettyPrint();
        assertEquals(postResponse.statusCode(), 201);
        assertEquals(postResponse.contentType(), "application/json");
        assertEquals(postResponse.path("success"), "A Spartan is Born!");
    }




    @Test
    public void postSpartan2(){

        Map<Object, Object> requestMap = new HashMap<>();


        requestMap.put("name", "Melon");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 9989118887552L);


        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().log().body()
                .body(requestMap)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .and().contentType("application/json")
                .and().body("success", equalTo("A Spartan is Born!"),
                        "data.name", equalTo(requestMap.get("name")),
                        "data.gender", equalTo(requestMap.get("gender")),
                        "data.phone", equalTo(requestMap.get("phone")));


    }

    @Test
    public void postSpartan3(){

        Spartan spartan = new Spartan();

        spartan.setName("Andrew");
        spartan.setGender("Male");
        spartan.setPhone(998914007898L);


        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartan)
                .when().post("/api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name", equalTo(spartan.getName()),
                        "data.gender", equalTo(spartan.getGender()),
                        "data.phone", equalTo(spartan.getPhone()));


    }



    @Test
    public void regionPostTest(){


        RegionPost regionPost = new RegionPost(10, "QA Uzb");

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and().log().body()
                .body(regionPost)
                .when().post(ConfigurationReader.getKey("hr_api_url") + "/regions/")
                .then()
                .statusCode(201)
                .and()
                .contentType("application/json");


    }





}
