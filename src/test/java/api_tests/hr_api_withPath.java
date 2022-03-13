package api_tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.*;


public class hr_api_withPath {



    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.getKey("hr_api_url");
    }



    @Test
    public void getCountriesWithPath(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"region_id\":2}")
                .when().get("/countries");


        assertEquals(response.statusCode(), 200);

        System.out.println(response.path("items[1].country_id").toString());
        System.out.println(response.path("items[1].country_name").toString());


        // save all results into a list
        List<Object> list = response.path("items");
        System.out.println("=========================================================================");
        for (Object o : list) {
            System.out.println(o);
        }


        System.out.println("=================================================================================================================================================");
        // get a specific link using . notation and indexes
        System.out.println(response.path("items.links[2].href[0]").toString());

        String canLink = response.path("items.links[2].href[0]");
        System.out.println("canLink = " + canLink);

        System.out.println("=================================================================================================================================================");

        // get all country names
        List<String> all_countryNames = response.path("items.country_name");
        System.out.println("all_countryNames = " + all_countryNames);
        System.out.println("================================================Region Ids Test========================================================================");

        // assert all region ids are equal to 2

        List<Integer> all_region_ids = response.path("items.region_id");
        for (int id : all_region_ids) {
            assertEquals(id,2);
            System.out.println("id = " + id);
        }






    }

    @Test
    public void job_idVerify(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");


        List<String> job_list = response.path("items.job_id");

        for (String s : job_list) {
            System.out.println("s = " + s);
            assertEquals(s,"IT_PROG");
        }


    }

}
