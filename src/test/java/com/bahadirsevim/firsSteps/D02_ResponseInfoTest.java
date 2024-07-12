package com.bahadirsevim.firsSteps;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D02_ResponseInfoTest {
    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().when().get(url);

        System.out.println("Status Code: "+response.getStatusCode());
        System.out.println("Content Type: "+response.getContentType());
        System.out.println("Server Header: "+response.getHeader("Server"));
        System.out.println("Status Line: "+response.getStatusLine());
        System.out.println("Response Time: "+response.getTime()+"ms");
    }
}
