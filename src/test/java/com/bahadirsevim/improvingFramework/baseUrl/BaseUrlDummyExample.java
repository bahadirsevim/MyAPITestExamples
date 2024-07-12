package com.bahadirsevim.improvingFramework.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlDummyExample {
    protected RequestSpecification specDummyAPI;

    @Before
    public void setup(){
        specDummyAPI = new RequestSpecBuilder()
                .setBaseUri("http://dummy.restapiexample.com/api/v1")
                .build();
    }
}
