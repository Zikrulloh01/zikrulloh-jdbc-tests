package api_tests.day8_PUT;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;



public class PUT_Demo {


    @BeforeClass
    public void beforeClass(){

        baseURI = ConfigurationReader.getKey("spartan_api_url");

    }




    @Test
    public void putVerify(){


        // create a map for put request json body
        Map<String,Object> map = new HashMap<>();
        map.put("name", "PutName1");
        map.put("gender", "Male");
        map.put("phone", 1231312321L);


        given().accept(ContentType.JSON)
                .pathParams("id", 178)
                .and()
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .log()
                .all()
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);


        Response response = given().accept(ContentType.JSON)
                .pathParams("id", 178)
                .when()
                .get("api/spartans/{id}");



        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.get("name"), map.get("name"));
        assertEquals(jsonPath.get("gender"), map.get("gender"));
        assertEquals(jsonPath.get("phone").toString(), map.get("phone").toString());

    }

    @Test
    public void patchVerify(){


        // create a map for put request json body
        Map<String,Object> map = new HashMap<>();
        map.put("name", "PatchName");


        given().accept(ContentType.JSON)
                .pathParams("id", 178)
                .and()
                .contentType(ContentType.JSON)
                .when()
                .body(map)
                .log()
                .all()
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);


        Response response = given().accept(ContentType.JSON)
                .pathParams("id", 178)
                .when()
                .get("api/spartans/{id}");



        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json");
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get("name"), map.get("name"));

    }

}
