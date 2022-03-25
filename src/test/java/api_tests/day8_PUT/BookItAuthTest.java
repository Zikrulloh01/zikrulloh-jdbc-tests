package api_tests.day8_PUT;

import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class BookItAuthTest {

    String accessToken="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDIwIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.jGs3jHQtTzXgRYVyIgciD_FdCEVpHyx05sp4m6t8wR8";

    @BeforeClass
    public void beforeClass(){
        baseURI="https://cybertek-reservation-api-qa2.herokuapp.com";
    }



    @Test
    public void getAllCampuses(){
        Response response = given().header("Authorization", accessToken)
                .when()
                .get("/api/campuses");


        response.prettyPrint();

        assertEquals(response.statusCode(), 200);


    }

}
