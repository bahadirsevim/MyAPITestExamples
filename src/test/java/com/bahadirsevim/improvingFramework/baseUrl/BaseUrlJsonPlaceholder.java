package com.bahadirsevim.improvingFramework.baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlJsonPlaceholder {
    protected RequestSpecification specJsonPlaceholderAPI;

    @Before
    public void setup(){
        specJsonPlaceholderAPI = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
    }

}
