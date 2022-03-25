package api_tests.Json_ResponseImplementations;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class SpartanGetRequest {


    String spartanUrl = "http://100.26.102.120:8000/api";



    @Test
    public void test1(){
        Response response = when().get(spartanUrl + "/spartans");

        System.out.println(response.statusCode());
        response.prettyPrint();

    }



    @Test
    public void test2(){
        Response response = when().get(spartanUrl + "/spartans/4");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Paige"));
    }




    @Test
    public void test3(){
        Response response = when().get(spartanUrl + "/hello");

        //verify status code
        Assert.assertEquals(response.getStatusCode(), 200);
        //verify content type
        Assert.assertEquals(response.getContentType(), "text/plain;charset=UTF-8");

        //verify headers
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        //verify content length
        Assert.assertEquals(response.getHeader("Content-Length"), "17");

        //verify body text
        Assert.assertEquals(response.body().asString(), "Hello from Sparta");

        System.out.println(response.header("Date"));
        System.out.println(response.header("Content-Length"));
        System.out.println(response.asString());
    }











}
