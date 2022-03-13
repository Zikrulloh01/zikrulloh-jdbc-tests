package api_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class simple_GetRequest {


    String hrUrl = "http://100.26.102.120:1000/ords/hr/regions";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrUrl);

        System.out.println(response.getStatusCode());

        System.out.println(response.getHeaders());
    }



    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(hrUrl);


        //Verify status code 200 with assertion
        Assert.assertEquals(response.getStatusCode(),200);

        Assert.assertEquals(response.getContentType(), "application/json");
        System.out.println(response.getContentType());

    }




    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json");
    }


    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON).when()
                .get(hrUrl + "/2");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }
}
