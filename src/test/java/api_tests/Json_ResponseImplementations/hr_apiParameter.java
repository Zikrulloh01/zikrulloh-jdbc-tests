package api_tests.Json_ResponseImplementations;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import  static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class hr_apiParameter {



    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("hr_api_url");
    }




    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 4)
                .when().get("/regions/{id}");

        System.out.println(response.path("region_name").toString());
        System.out.println(response.path("region_id").toString());


    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        System.out.println(response.asString());
    }
}
