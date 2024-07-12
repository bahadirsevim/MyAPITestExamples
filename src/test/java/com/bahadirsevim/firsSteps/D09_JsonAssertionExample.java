package com.bahadirsevim.firsSteps;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class D09_JsonAssertionExample {
    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking";
        JSONObject requestBody = new JSONObject();
        JSONObject reservationDates = new JSONObject();

        reservationDates.put("checkin","2023-01-10");
        reservationDates.put("checkout","2023-01-20");

        requestBody.put("firstname", "Jane");
        requestBody.put("lastname", "Doe");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", reservationDates);
        requestBody.put("additionalneeds", "wi-fi");


        JSONObject expectedData = new JSONObject();
        expectedData.put("bookingId", 24);
        expectedData.put("booking", requestBody);

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);


        JsonPath responseJsonPath = response.jsonPath();
        assertEquals(expectedData.getJSONObject("booking").get("firstname"), responseJsonPath.get("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").get("lastname"), responseJsonPath.get("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").get("totalprice"), responseJsonPath.get("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"), responseJsonPath.get("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"), responseJsonPath.get("booking.additionalneeds"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), responseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), responseJsonPath.get("booking.bookingdates.checkout"));


    }
}
