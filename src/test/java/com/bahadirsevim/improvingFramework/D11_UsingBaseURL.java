package com.bahadirsevim.improvingFramework;

import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D11_UsingBaseURL extends BaseUrlJsonPlaceholder {
    @Test
    public void test01(){
        specJsonPlaceholderAPI.pathParam("pathParam1", "posts");

        Response response = given()
                .when().spec(specJsonPlaceholderAPI)
                .get("/{pathParam1}");

        response.then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.hasSize(100));
    }

    @Test
    public void test02(){
        specJsonPlaceholderAPI.pathParams("pp1", "posts", "pp2", 44);

        Response response = given()
                .when().spec(specJsonPlaceholderAPI)
                .get("/{pp1}/{pp2}");

        response.then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.equalTo("optio dolor molestias sit"));
    }
}
