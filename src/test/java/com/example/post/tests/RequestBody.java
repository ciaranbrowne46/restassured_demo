package com.example.post.tests;

import org.json.JSONObject;

public class RequestBody {
	public RequestBody() {
	}

	public static String createRequestBody(final String name, final String age, final String salary, final String image) {
		JSONObject data = new JSONObject();
		 
        data.put("employee_name", name);
        data.put("employee_age", age);
        data.put("employee_salary", salary);
        data.put("profile_image", image);
        
        return data.toString();
	}
}