package reviews;

import api_tests.day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ZippoApiTesting {


    @BeforeClass
    public void beforeClass(){
        baseURI=ConfigurationReader.getKey("zip_api_url");
    }




    @Test
    public void verifyApiConnected(){


        given().log().all()
                .accept(ContentType.JSON)
                .and().pathParams("zip", 22031)
                .when()
                .get("/us/{zip}")
                .then()
                .log().body()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json");


    }


    @Test
    public void testWithJsonPath(){


        Response response = given().log().all()
                .accept(ContentType.JSON)
                .pathParams("zip", "22031")
                .and()
                .when().get("/us/{zip}");
        JsonPath jsonPath = response.jsonPath();


        //verify postCode
        assertEquals(jsonPath.get("'post code'"), "22031");


        //verify country
        assertEquals(jsonPath.get("country"), "United States");


        //verify countryAbbreviation
        assertEquals(jsonPath.get("'country abbreviation'"),"US");

        assertEquals(jsonPath.get("places[0].state"), "Virginia");


        System.out.println(jsonPath.get("places[0].state").toString());


    }





    @Test
    public void negativeTest(){


        given().accept(ContentType.JSON)
                .pathParams("zip", 50000)
                .when()
                .get("/us/{zip}")
                .then()
                .log().all()
                .and()
                .statusCode(404)
                .and()
                .contentType("application/json");
    }

}
