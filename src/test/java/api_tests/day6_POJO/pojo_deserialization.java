package api_tests.day6_POJO;


import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Map;

import static org.testng.Assert.*;
import  static io.restassured.RestAssured.*;




public class pojo_deserialization {



    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }


    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        Spartan spartan15 = response.body().as(Spartan.class);
        System.out.println("spartan15 = " + spartan15.toString());
    }



    @Test
    public void regionToPojo(){
        Response response = when().get(ConfigurationReader.getKey("hr_api_url") + "/regions");

        assertEquals(response.statusCode(), 200);

        Regions regions = response.body().as(Regions.class);
        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());
        System.out.println(regions.getItems().get(0).getRegionName());
        System.out.println(regions.getItems().get(1).getRegionId());
    }




    @Test
    public void gson_example(){
        Gson gson = new Gson();
        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println("map = " + map);
        Spartan spartan = gson.fromJson(myJsonData, Spartan.class);
        System.out.println("spartan = " + spartan);

        // -----------------------Serialization from Java to JSon---------------------------
        Spartan spartan1 = new Spartan(200, "Jack", "Male", 975942001);
        String s = gson.toJson(spartan1);
        System.out.println(s);
    }





}
