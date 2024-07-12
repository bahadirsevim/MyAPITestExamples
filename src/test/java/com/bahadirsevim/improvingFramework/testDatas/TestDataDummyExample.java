package com.bahadirsevim.improvingFramework.testDatas;

import org.json.JSONObject;

public class TestDataDummyExample {
    public static int successStatusCode = 200;
    public static String contentType = "application/json";

    public static JSONObject getExpectedBody(int id, String employee_name, int employee_salary, int employee_age, String profile_image){
        JSONObject wholeExpectedData = new JSONObject();
        JSONObject expectedData = new JSONObject();

        expectedData.put("id", id);
        expectedData.put("employee_name", employee_name);
        expectedData.put("employee_salary", employee_salary);
        expectedData.put("employee_age", employee_age);
        expectedData.put("profile_image", profile_image);

        wholeExpectedData.put("status", "success");
        wholeExpectedData.put("data", expectedData);
        wholeExpectedData.put("message", "Successfully! Record has been fetched.");

        return wholeExpectedData;
    }
}
