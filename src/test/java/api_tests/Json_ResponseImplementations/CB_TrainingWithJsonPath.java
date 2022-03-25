package api_tests.Json_ResponseImplementations;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static org.testng.Assert.*;
import java.util.List;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;


public class CB_TrainingWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI = ConfigurationReader.getKey("cb_api_url");
    }


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id", 24686)
                .when().get("/student/{id}");



        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();


        String f_name = jsonPath.get("students[0].firstName").toString();
        String l_name = jsonPath.get("students[0].lastName").toString();

        System.out.println("f_name = " + f_name);
        System.out.println("l_name = " + l_name);

        String phone = jsonPath.get("students[0].contact.phone");

        System.out.println("phone = " + phone);


        // get cityName and zipcode

        String cityName = jsonPath.getString("students[0].company.address.city");
        String zipCode = jsonPath.getString("students[0].company.address.zipCode");

        assertEquals(cityName, "Chicago");
        assertEquals(zipCode, "887433");

        System.out.println("cityName = " + cityName);
        System.out.println("zipCode = " + zipCode);


        String first_names = jsonPath.getString("students.firstName"); // it gets the list as a string which response.path doesn't do
        System.out.println("first_names = " + first_names);







    }


}
