package com.expedia.assignment;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.expedia.assignment.validator.HotelDealsValidator;

public class HotelDealsValidatorTest {

	
	public HotelDealsValidatorTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testEmptyOrNull() {
		Assert.assertTrue(HotelDealsValidator.validate(new HashMap<String, String>()).isEmpty());
		Assert.assertTrue(HotelDealsValidator.validate(null).isEmpty());
	}
	
	@Test
	public void testValidMinDateFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTripStartDate", "2018-05-13");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMinDateFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTripStartDate", "2018-5-13");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Invalid Min. Start Date. format must be \"yyyy-MM-dd\"");
	}
	
	@Test
	public void testValidMaxDateFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxTripStartDate", "2018-05-13");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMaxDateFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxTripStartDate", "2018-5-13");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Invalid Max. Start Date. format must be \"yyyy-MM-dd\"");
	}
	
	@Test
	public void testValidMinMaxDateRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTripStartDate", "2017-05-13");
		params.put("maxTripStartDate", "2018-05-13");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMinMaxDateRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTripStartDate", "2019-05-13");
		params.put("maxTripStartDate", "2018-05-13");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min date can not be after the Max date");
	}
	
	@Test
	public void testValidLengthOfStay() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lengthOfStay", "5");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidLengthOfStayFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lengthOfStay", "invalidFormat");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Length Of Stay must be a number.");
	}
	
	@Test
	public void testNigativeLengthOfStayFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lengthOfStay", "-1");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Length Of Stay must be greater than Zero");
	}
	
	@Test
	public void testValidStarRating() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minStarRating", "3");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMinStarRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minStarRating", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min. Start Rating must be a number between 0 - 5");
	}
	
	@Test
	public void testInvalidMaxStarRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxStarRating", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Start Ratingg must be a number between 0 -5");
	}
	
	@Test
	public void testInvalidMinMaxStarRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minStarRating", "5");
		params.put("maxStarRating", "2");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Start Rating MUST be greater than Min. Start Rating");
	}
	
	@Test
	public void testValidTotalRating() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTotalRate", "3");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMinTotalRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTotalRate", "invalidFormat");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min. Total Rating must be a number between 0 - 5");
	}
	
	@Test
	public void testInvalidMinTotalRatingRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTotalRate", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min. Total Rating must be a number between 0 - 5");
	}
	
	@Test
	public void testInvalidMaxTotalStarRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxTotalRate", "invalidFormat");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Total Rating must be a number between 0 -5");
	}
	
	@Test
	public void testInvalidMaxTotalStarRatingRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxTotalRate", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Total Rating must be a number between 0 -5");
	}
	
	@Test
	public void testInvalidMinMaxTotalRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minTotalRate", "5");
		params.put("maxTotalRate", "2");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Total Rating MUST be greater than Min. Total Rating");
	}
	
	@Test
	public void testValidGuestRating() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minGuestRating", "3");
		Assert.assertTrue(HotelDealsValidator.validate(params).isEmpty());
	}
	
	@Test
	public void testInvalidMinGuestRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minGuestRating", "invalidFormat");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min. Guest Rating must be a number between 0 - 5");
	}
	
	@Test
	public void testInvalidMinGuestRatingRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minGuestRating", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Min. Guest Rating must be a number between 0 - 5");
	}
	
	@Test
	public void testInvalidMaxGuestStarRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxGuestRating", "invalidFormat");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Guest Rating must be a number between 0 -5");
	}
	
	@Test
	public void testInvalidMaxGuestStarRatingRange() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("maxGuestRating", "7");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Guest Rating must be a number between 0 -5");
	}
	
	@Test
	public void testInvalidMinMaxGuestRatingFormat() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("minGuestRating", "5");
		params.put("maxGuestRating", "2");
		Assert.assertEquals(HotelDealsValidator.validate(params).get(0), "Max. Guest Rating MUST be greater than Min. Guest Rating");
	}
	
}
