package com.expedia.assignment.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class DealServices {

	private static final String TARGET = "https://offersvc.expedia.com";
	private static final String PATH = "offers/v2/getOffers";

	private DealServices() {

	}

	public static JSONObject getHotelDeals(Map<String, String> params) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(TARGET).path(PATH);

		params.putAll(getDefaultParams());

		for (Map.Entry<String, String> entry : params.entrySet()) {
			if (entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue())) {
				target = target.queryParam(entry.getKey(), entry.getValue());
			}
		}
		
		Builder requestBuilder = target.request(MediaType.APPLICATION_JSON);
		return new JSONObject(requestBuilder.get().readEntity(String.class));
	}

	private static Map<String, String> getDefaultParams() {
		Map<String,String> defaultParams = new HashMap<String, String>();
		
		defaultParams.put("scenario", "deal-finder");
		defaultParams.put("page", "foo");
		defaultParams.put("uid", "foo");
		defaultParams.put("productType", "Hotel");
		
		return defaultParams;
	}

}
