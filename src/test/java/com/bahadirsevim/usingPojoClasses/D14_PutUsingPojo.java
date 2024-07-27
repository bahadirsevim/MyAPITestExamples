package com.bahadirsevim.usingPojoClasses;

import com.bahadirsevim.improvingFramework.baseUrl.BaseUrlJsonPlaceholder;
import com.bahadirsevim.improvingFramework.testDatas.TestDataJsonPlaceholder;
import com.bahadirsevim.usingPojoClasses.pojos.PojoJsonPlaceHolder;
import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class D14_PutUsingPojo extends BaseUrlJsonPlaceholder {
    @Test
    public void test01(){
        specJsonPlaceholderAPI.pathParams("pp1","posts","pp2",70);

        PojoJsonPlaceHolder request = new PojoJsonPlaceHolder("Ahmet", "Merhaba", 10,70);

        PojoJsonPlaceHolder expectedData = new PojoJsonPlaceHolder("Ahmet", "Merhaba", 10,70);

        Response response = given().spec(specJsonPlaceholderAPI).contentType(ContentType.JSON)
                .when().body(request)
                .put("{pp1}/{pp2}");

        PojoJsonPlaceHolder responsePojo = response.as(PojoJsonPlaceHolder.class);

        Assert.assertEquals(TestDataJsonPlaceholder.successResponseCode, response.statusCode());
        Assert.assertEquals(TestDataJsonPlaceholder.contentType, response.contentType());
        Assert.assertEquals(expectedData.getTitle(), responsePojo.getTitle());
        Assert.assertEquals(expectedData.getBody(), responsePojo.getBody());
        Assert.assertEquals(expectedData.getUserId(), responsePojo.getUserId());
        Assert.assertEquals(expectedData.getId(), responsePojo.getId());



    }
}
