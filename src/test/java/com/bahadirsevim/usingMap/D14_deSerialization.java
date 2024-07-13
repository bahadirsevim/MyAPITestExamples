package com.bahadirsevim.usingMap;

import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlJsonPlaceholder;
import com.bahadirsevim.improvingFramework.testDatas.TestDataJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class D14_deSerialization extends BaseUrlJsonPlaceholder {
    @Test
    public void test01(){
        specJsonPlaceholderAPI.pathParams("pp1","posts","pp2",70);
        Map<String, Object> requestBodyMap = TestDataJsonPlaceholder.getBodyAsMap();
        Map<String, Object> expectedData = TestDataJsonPlaceholder.getBodyAsMap();

        Response response = given().spec(specJsonPlaceholderAPI).contentType(ContentType.JSON)
                .when().body(requestBodyMap)
                .put("{pp1}/{pp2}");

        Map<String, Object> responseMap = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("title"), responseMap.get("title"));
        Assert.assertEquals(expectedData.get("body"), responseMap.get("body"));
        Assert.assertEquals(expectedData.get("userId"), responseMap.get("userId"));
        Assert.assertEquals(expectedData.get("id"), responseMap.get("id"));
    }
}
