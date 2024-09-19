package com.luv2shopdev.ecommerce.utils;

import java.util.Map;
import java.util.Random;

import net.minidev.json.JSONObject;

public class Utility {

	Random random = new Random();

	public JSONObject createPaymentIntentLocal(Map<String, Object> params) {

		JSONObject jsonObject = new JSONObject();

	//	long randomNum = random.nextLong(1000000000);

		//jsonObject.put("id", "pi_luv2shop_" + randomNum);
		jsonObject.put("object", "payment_intent");
		jsonObject.put("capture_method", "automatic");
		jsonObject.put("created", System.currentTimeMillis());
		jsonObject.putAll(params);

		return jsonObject;
	}
}
