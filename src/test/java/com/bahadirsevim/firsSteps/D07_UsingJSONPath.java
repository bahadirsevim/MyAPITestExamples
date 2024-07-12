package com.bahadirsevim.firsSteps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class D07_UsingJSONPath {
    @Test
    public void test01(){
        JSONObject userInfoJson = new JSONObject();

        JSONObject userAddressJson = new JSONObject();
        userAddressJson.put("streetAddress", "naist street");
        userAddressJson.put("city", "Nara");
        userAddressJson.put("postalCode", "630-0192");

        JSONArray userPhoneListJson = new JSONArray();

        JSONObject userCellPhoneJson = new JSONObject();
        userCellPhoneJson.put("type", "iPhone");
        userCellPhoneJson.put("number", "0123-4567-8888");

        JSONObject userHomePhoneJson = new JSONObject();
        userHomePhoneJson.put("type", "home");
        userHomePhoneJson.put("number", "0123-4567-8888");

        userPhoneListJson.put(userCellPhoneJson);
        userPhoneListJson.put(userHomePhoneJson);

        userInfoJson.put("firstName", "John");
        userInfoJson.put("lastName", "doe");
        userInfoJson.put("age", 26);
        userInfoJson.put("address", userAddressJson);
        userInfoJson.put("phoneNumbers", userPhoneListJson);

        System.out.println(userInfoJson);
        System.out.println(userInfoJson.get("firstName"));
        System.out.println(userInfoJson.getJSONObject("address").get("streetAddress"));
        System.out.println(userInfoJson.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));
    }

    @Test
    public void test02(){
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();

        // Önce içerideki json oluşturulur.
        JSONObject reservationDates = new JSONObject();
        reservationDates.put("checkin","2023-01-10");
        reservationDates.put("checkout","2023-01-20");

        // içerideki json ile birlikte bilgiler toplanır.
        requestBody.put("firstname", "Jane");
        requestBody.put("lastname", "Doe");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", reservationDates);
        requestBody.put("additionalneeds", "wi-fi");

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        response.prettyPrint();

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Jane"),
                        "booking.lastname", equalTo("Doe"),
                        "booking.totalprice", equalTo(500),
                        "booking.depositpaid", equalTo(false),
                        "booking.bookingdates.checkin", equalTo("2023-01-10"),
                        "booking.bookingdates.checkout", equalTo("2023-01-20")
                );
    }
}
