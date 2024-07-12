package com.bahadirsevim.improvingFramework;

import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlDummyExample;
import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlJsonPlaceholder;
import com.bahadirsevim.improvingFramework.testDatas.TestDataJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class D12_UsingTestData extends BaseUrlJsonPlaceholder {
    @Test
    public void test01(){
        specJsonPlaceholderAPI.pathParams("pp1", "posts", "pp2", "22");

        JSONObject expectedData = TestDataJsonPlaceholder.getResponseBody22();

        Response response = given().spec(specJsonPlaceholderAPI).
                when().
                get("{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();
        Assert.assertEquals(TestDataJsonPlaceholder.successResponseCode, response.statusCode());
        Assert.assertEquals(expectedData.get("userId"), responseJsonPath.get("userId"));
        Assert.assertEquals(expectedData.get("id"), responseJsonPath.get("id"));
        Assert.assertEquals(expectedData.get("title"), responseJsonPath.get("title"));
        Assert.assertEquals(expectedData.get("body"), responseJsonPath.get("body"));
    }

    @Test
    public void test02(){
        int id = 22;
        specJsonPlaceholderAPI.pathParams("pp1", "posts","pp2", id);

        JSONObject expectedData = TestDataJsonPlaceholder.getResponseBody(
                3,
                22,
                "dolor sint quo a velit explicabo quia nam",
                "eos qui et ipsum ipsam suscipit aut\n" +
                        "sed omnis non odio\n" +
                        "expedita earum mollitia molestiae aut atque rem suscipit\n" +
                        "nam impedit esse");

        Response response = given().spec(specJsonPlaceholderAPI)
                .when()
                .get("{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), TestDataJsonPlaceholder.successResponseCode);
        softAssert.assertEquals(responseJsonPath.get("userId"), expectedData.get("userId"));
        softAssert.assertEquals(responseJsonPath.get("id"), expectedData.get("id"));
        softAssert.assertEquals(responseJsonPath.get("title"), expectedData.get("title"));
        softAssert.assertEquals(responseJsonPath.get("body"), expectedData.get("body"));
    }
}
