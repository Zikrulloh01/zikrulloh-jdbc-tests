package api_tests.day8_PUT;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class delete_Demo {



    @BeforeClass
    public void beforeClass(){
        baseURI=ConfigurationReader.getKey("spartan_api_url");
    }




    @Test
    public void delete_Test(){
        given().log().all()
                .pathParams("id", 195)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(204)
                .and()
                .log().body();
    }
}
