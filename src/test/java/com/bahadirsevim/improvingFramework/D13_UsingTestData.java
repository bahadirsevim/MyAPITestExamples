package com.bahadirsevim.improvingFramework;

import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlDummyExample;
import com.bahadirsevim.improvingFramework.testDatas.TestDataDummyExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D13_UsingTestData extends BaseUrlDummyExample {
    @Test
    public void test01(){
        specDummyAPI.pathParams("pp1", "employee", "pp2", 3);

        JSONObject expectedData = TestDataDummyExample.getExpectedBody(
                3,
                "Ashton Cox",
                86000,
                66,
                ""
        );

        Response response = given().spec(specDummyAPI).
                when().
                get("{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();
        Assert.assertEquals(TestDataDummyExample.successStatusCode, response.statusCode());
        Assert.assertEquals(TestDataDummyExample.contentType, response.contentType());
        Assert.assertEquals(expectedData.getJSONObject("data").get("profile_image"), responseJsonPath.get("data.profile_image"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_name"), responseJsonPath.get("data.employee_name"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_salary"), responseJsonPath.get("data.employee_salary"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_age"), responseJsonPath.get("data.employee_age"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("id"), responseJsonPath.get("data.id"));
        Assert.assertEquals(expectedData.get("message"), responseJsonPath.get("message"));
        Assert.assertEquals(expectedData.get("status"), responseJsonPath.get("status"));
    }
}
