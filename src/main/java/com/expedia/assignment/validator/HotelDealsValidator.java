package com.expedia.assignment.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class HotelDealsValidator {

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static String DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";

	public static List<String> validate(Map<String, String> params) {
		
		if(params == null || params.isEmpty()) {
			return new ArrayList<String>(0);
		}

		List<String> errors = new ArrayList<String>();

		errors.addAll(validateTripStartDate(params.get("minTripStartDate"), params.get("maxTripStartDate")));
		errors.addAll(validateLengthOfStay(params.get("lengthOfStay")));
		errors.addAll(validateStarRating(params.get("minStarRating"), params.get("maxStarRating")));
		errors.addAll(validateTotalRate(params.get("minTotalRate"), params.get("maxTotalRate")));
		errors.addAll(validateGuestRating(params.get("minGuestRating"), params.get("maxGuestRating")));

		return errors;
	}

	private static boolean validateRating(String rating) {
		if (StringUtils.isEmpty(rating)) {
			return true;
		}

		try {
			Double rate = Double.valueOf(rating);
			if (rate >= 0 && rate <= 5) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}

	private static boolean validateRatingRange(String minRating, String maxRating) {

		if (StringUtils.isEmpty(minRating) || StringUtils.isEmpty(maxRating)) {
			return true;
		}

		try {
			if (Double.valueOf(minRating) < Double.valueOf(maxRating)) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}

	private static List<String> validateGuestRating(String minGuestRating, String maxGuestRating) {
		List<String> errors = new ArrayList<String>();

		if (!validateRating(minGuestRating)) {
			errors.add("Min. Guest Rating must be a number between 0 - 5");
		}

		if (!validateRating(maxGuestRating)) {
			errors.add("Max. Guest Rating must be a number between 0 -5");
		}

		if (!validateRatingRange(minGuestRating, maxGuestRating)) {
			errors.add("Max. Guest Rating MUST be greater than Min. Guest Rating");
		}

		return errors;
	}

	private static List<String> validateTotalRate(String minTotalRate, String maxTotalRate) {

		List<String> errors = new ArrayList<String>();

		if (!validateRating(minTotalRate)) {
			errors.add("Min. Total Rating must be a number between 0 - 5");
		}

		if (!validateRating(maxTotalRate)) {
			errors.add("Max. Total Rating must be a number between 0 -5");
		}

		if (!validateRatingRange(minTotalRate, maxTotalRate)) {
			errors.add("Max. Total Rating MUST be greater than Min. Total Rating");
		}

		return errors;
	}

	private static List<String> validateStarRating(String minStarRating, String maxStarRating) {

		List<String> errors = new ArrayList<String>(5);

		if (!validateRating(minStarRating)) {
			errors.add("Min. Start Rating must be a number between 0 - 5");
		}

		if (!validateRating(maxStarRating)) {
			errors.add("Max. Start Ratingg must be a number between 0 -5");
		}

		if (!validateRatingRange(minStarRating, maxStarRating)) {
			errors.add("Max. Start Rating MUST be greater than Min. Start Rating");
		}

		return errors;
	}

	private static List<String> validateLengthOfStay(String lengthOfStay) {
		List<String> errors = new ArrayList<String>(4);
		Integer length = null;

		if (StringUtils.isNotEmpty(lengthOfStay)) {
			try {
				length = Integer.valueOf(lengthOfStay);
			} catch (NumberFormatException e) {
				errors.add("Length Of Stay must be a number.");
			}
		}

		if (length != null && length < 1) {
			errors.add("Length Of Stay must be greater than Zero");
		}

		return errors;
	}

	private static List<String> validateTripStartDate(String minStartDate, String maxStartDate) {
		List<String> errors = new ArrayList<String>(5);

		Date minDate = null;
		Date maxDate = null;

		if (StringUtils.isNotEmpty(minStartDate)) {
			try {
				if (!minStartDate.matches(DATE_FORMAT_REGEX)) {
					errors.add("Invalid Min. Start Date. format must be \"yyyy-MM-dd\"");
				}
				minDate = DATE_FORMAT.parse(minStartDate);
			} catch (ParseException e) {
				errors.add("Invalid Min. Start Date. format must be \"yyyy-MM-dd\"");
			}
		}

		if (StringUtils.isNotEmpty(maxStartDate)) {
			try {
				if (!maxStartDate.matches(DATE_FORMAT_REGEX)) {
					errors.add("Invalid Max. Start Date. format must be \"yyyy-MM-dd\"");
				}
				maxDate = DATE_FORMAT.parse(maxStartDate);
			} catch (ParseException e) {
				errors.add("Invalid Max. Start Date. format must be \"yyyy-MM-dd\"");
			}

		}

		if (!errors.isEmpty()) {
			return errors;
		}

		if (minDate != null && maxDate != null) {
			if (minDate.after(maxDate)) {
				errors.add("Min date can not be after the Max date");
			}
		}
		return errors;
	}
}
