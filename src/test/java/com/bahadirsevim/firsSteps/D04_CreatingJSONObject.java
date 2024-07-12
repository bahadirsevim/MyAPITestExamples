package com.bahadirsevim.firsSteps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D04_CreatingJSONObject {
    @Test
    public void test01(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title","Bahadir");
        jsonObject.put("body","Hello");
        jsonObject.put("userId", 1);

        System.out.println(jsonObject);
    }
    @Test
    public void test02(){
        // Önce Inner Json objesi oluşturulur.
        JSONObject dateJsonObject = new JSONObject();
        dateJsonObject.put("checkin", "2018-01-01");
        dateJsonObject.put("checkout", "2019-01-01");

        // Ana Json Objesi oluşturulup Inner objeye atıf yapılır.
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstname", "Jim");
        requestBody.put("additionalneeds", "Breakfast");
        requestBody.put("bookingdates", dateJsonObject);
        requestBody.put("totalprice", 111);
        requestBody.put("depositpaid", true);
        requestBody.put("lastname", "Brown");

        System.out.println(requestBody);
    }
    @Test
    public void test03(){
        String url = "https://jsonplaceholder.typicode.com/posts/70";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Bahadir");
        jsonObject.put("body", "Merhaba");
        jsonObject.put("userID", 10);
        jsonObject.put("id", 70);

        Response response = given().contentType(ContentType.JSON)
                            .when().body(jsonObject.toString())
                            .put(url);

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }
}
