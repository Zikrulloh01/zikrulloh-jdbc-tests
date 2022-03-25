package reviews;

import api_tests.day6_POJO.Spartan;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


public class PojoPractice {


    @BeforeClass
    public void beforeClass(){
        baseURI=ConfigurationReader.getKey("zip_api_url");
    }




    @Test
    public void PojoTestWithSpartan(){
        Response response = given().accept(ContentType.JSON)
                .pathParams("id", 7)
                .when()
                .get(ConfigurationReader.getKey("spartan_api_url") + "/api/spartans/{id}");

        Spartan spartan7 = response.as(Spartan.class);
        System.out.println(spartan7.toString());
    }





    @Test
    public void testApiWithPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParams("zip", "22031")
                .and()
                .when()
                .get("/us/{zip}");

        Gson gson = new Gson();



        // convert from Json to PostCode class using gson
        PostCode postCode = gson.fromJson(response.body().asString(), PostCode.class);
        System.out.println("postCode = " + postCode);


        // assign response body into PostCode object as.(Class.class)
        PostCode postCode1 = response.as(PostCode.class);
        System.out.println("postCode1 = " + postCode1);
        System.out.println(postCode.getPlaces().get(0).getLatitude());
        assertEquals(postCode.getPlaces().get(0).getLatitude(), "38.8604");
        assertEquals(postCode.getCountryAbbreviation(), "US");

    }




}
