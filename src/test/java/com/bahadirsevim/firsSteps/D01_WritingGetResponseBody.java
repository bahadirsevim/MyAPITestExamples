package com.bahadirsevim.firsSteps;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D01_WritingGetResponseBody {
    @Test
    public void get01(){
        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().when().get(url);
        response.prettyPrint();
    }

}
