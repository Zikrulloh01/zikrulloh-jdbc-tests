package api_tests.Json_ResponseImplementations;


import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;
import  static io.restassured.RestAssured.*;
public class HamcrestMatchersApi {



    @Test
    public void OneSpartansWithHamcrest(){

        given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("http://100.26.102.120:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().body("id",equalTo(15),
                        "name", equalTo("Meta"),
                                        "gender", equalTo("Female"),
                                        "phone", equalTo(1938695106));


    }


    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .and().pathParams("id", 6766)
                .when().get(ConfigurationReader.getKey("cb_api_url") + "/teacher/{id}")
                .then().statusCode(200).and().contentType("application/json")
                .and().assertThat().header("Content-Length", equalTo("242"))
                .and().header("Connection", "close")
                .and().header("Date", notNullValue())
                .and().assertThat().body("teachers[0].firstName", equalTo("ZX"),
                        "teachers[0].lastName", equalTo("AS"),
                        "teachers[0].gender", equalTo("Male"))
                .log().all();
    }



    @Test
    public void teachersWithDepartments(){
        given().accept(ContentType.JSON)
                .and().pathParams("name", "Computer")
                .when().get(ConfigurationReader.getKey("cb_api_url") + "/teacher/department/{name}")
                .then().log().all().statusCode(200).and().contentType("application/json")
                .and().assertThat().body("teachers.firstName", hasItems("Alexander", "Marteen"));
    }

}
