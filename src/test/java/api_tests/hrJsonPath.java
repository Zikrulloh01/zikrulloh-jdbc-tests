package api_tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import  static io.restassured.RestAssured.*;

public class hrJsonPath {


    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.getKey("hr_api_url");
    }


    @Test
    public void test1(){


        Response response = get("/countries");


        JsonPath jsonPath = response.jsonPath();

        String australia = jsonPath.get("items[1].country_name");
        System.out.println(australia);


        //get all country Id's
        System.out.println("===========================================================================================================================");
        List<String> list = jsonPath.get("items.country_id");

        System.out.println("list = " + list);



        // get all country name where region_id = 2


        List<String> country_names = jsonPath.get("items.findAll{it.region_id==2}.country_name");

        System.out.println("country_names = " + country_names);


    }



    @Test
    public void test2(){


        Response response = given().queryParams("limit", 107)
                .when().get("/employees");


        JsonPath jsonPath = response.jsonPath();


        List<String> fNames = jsonPath.get("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println("fNames = " + fNames);



        List<String> fNames2 = jsonPath.get("items.findAll{it.salary>10000}.first_name");
        System.out.println("fNames2 = " + fNames2);

        List<String> f_name = jsonPath.get("items.findAll{it.salary<2200}.email");
        System.out.println("f_name = " + f_name);


        // find max salary

        String stevenName  = jsonPath.get("items.max{it.salary}.first_name");
        System.out.println("stevenName = " + stevenName);


        //sum of all salary
        int sumSalary = jsonPath.get("items.sum{it.salary}");
        System.out.println("sumSalary = " + sumSalary);


    }

}
