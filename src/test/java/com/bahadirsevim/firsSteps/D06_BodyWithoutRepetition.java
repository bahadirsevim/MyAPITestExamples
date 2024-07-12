package com.bahadirsevim.firsSteps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class D06_BodyWithoutRepetition {
    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().when().get(url);

        /*
        Çok fazla Matchers kullandık. Farklı bir yöntem göstereceğim.
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Susan"))
                .body("lastname", Matchers.equalTo("Jackson"))
                .body("totalprice", Matchers.lessThan(1000))
                .body("depositpaid", Matchers.equalTo(false))
                .body("additionalneeds", Matchers.notNullValue());
         */

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(
                        "firstname", equalTo("Susan"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", lessThan(1000),
                        "depositpaid", equalTo(false),
                        "additionalneeds", notNullValue()
                );
    }
}
