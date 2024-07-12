package com.bahadirsevim.firsSteps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D05_ResponseBodyTest {
    @Test
    public void test01(){
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        Response response = given().when().get(url);

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.equalTo(5))
                .body("title", Matchers.equalTo("optio dolor molestias sit"));
    }
    @Test
    public void test02(){
        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "API");
        jsonObject.put("body", "API ogrenmek ne guzel");
        jsonObject.put("userId", 10);

        Response response =
                given().contentType(ContentType.JSON)
                .when().body(jsonObject.toString()).post(url);

        response.then().assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.lessThan(100))
                .body("body", Matchers.containsString("API"));
    }
}
