package api_tests.Json_ResponseImplementations;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import  static io.restassured.RestAssured.*;

public class jsonToCollection {




    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("spartan_api_url");
    }



    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 15)
                .when().get("/api/spartans/{id}");
        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);
        String name = jsonMap.get("name").toString();
        assertEquals(name, "Meta");
    }




    @Test
    public void allSpartansToListOfMaps(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");



        assertEquals(response.statusCode(), 200);


        List<Map<String, Object>> listOfMaps = response.body().as(List.class);

        System.out.println("listOfMaps = " + listOfMaps);

        //second spartan first_name

        System.out.println(listOfMaps.get(0).get("name"));

        Map<String, Object> objectMap = listOfMaps.get(3);

        System.out.println(objectMap);
    }




    @Test
    public void regionToMaps(){
        Response response = given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.getKey("hr_api_url") + "/regions");



        assertEquals(response.statusCode(), 200);


        Map<String,Object> regionMap = response.body().as(Map.class);

        System.out.println("regionMap = " + regionMap);

        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));

        System.out.println("regionMap.get(\"hasMore\") = " + regionMap.get("hasMore"));

        List<Map<String,Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");

        System.out.println(itemsList);
        System.out.println("itemsList.get(0).get(\"region_name\") = " + itemsList.get(0).get("region_name"));

        System.out.println("itemsList.get(0).get(\"link\") = " + itemsList.get(0).get("links"));


    }

}
