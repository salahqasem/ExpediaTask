package com.expedia.assignment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.expedia.assignment.service.DealServices;
import com.expedia.assignment.validator.HotelDealsValidator;

@Singleton
@Path("/deals")
public class HotelDealsController {

	@Path("/hotels")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getHotelDeals(@Context HttpServletRequest request) {

		JSONObject response = new JSONObject();

		Map<String, String> params = extractParams(request);
		List<String> errors = HotelDealsValidator.validate(params);
		if (errors != null && !errors.isEmpty()) {
			JSONArray array = new JSONArray(errors);
			response.put("errors", array);
			return Response.ok().entity(response.toString()).build();
		}

		return Response.ok().entity(DealServices.getHotelDeals(params).toString()).build();

	}

	private Map<String, String> extractParams(HttpServletRequest request) {

		Map<String, String> params = new HashMap<String, String>();

		params.put("destinationName", request.getParameter("destinationName"));
		params.put("minTripStartDate", request.getParameter("minTripStartDate"));
		params.put("maxTripStartDate", request.getParameter("maxTripStartDate"));
		params.put("lengthOfStay", request.getParameter("lengthOfStay"));
		params.put("minStarRating", request.getParameter("minStarRating"));
		params.put("maxStarRating", request.getParameter("maxStarRating"));
		params.put("minTotalRate", request.getParameter("minTotalRate"));
		params.put("maxTotalRate", request.getParameter("maxTotalRate"));
		params.put("minGuestRating", request.getParameter("minGuestRating"));
		params.put("maxGuestRating", request.getParameter("maxGuestRating"));

		return params;
	}

}
