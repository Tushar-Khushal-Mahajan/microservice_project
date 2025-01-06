package com.ratings.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.entities.Ratings;
import com.ratings.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	private RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	// --------

	@GetMapping
	public ResponseEntity<List<Ratings>> getRatings() {

		List<Ratings> allRatings = ratingService.getAllRatings();

		return ResponseEntity.status(HttpStatus.OK).body(allRatings);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Ratings>> getRatingsByUserId(@PathVariable String userId) {

		List<Ratings> ratingsByUserId = ratingService.getRatingsByUserId(userId);

		return ResponseEntity.status(HttpStatus.OK).body(ratingsByUserId);
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Ratings>> getRatingsByHotelId(@PathVariable String hotelId) {

		List<Ratings> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);

		return ResponseEntity.status(HttpStatus.OK).body(ratingsByHotelId);

	}

	@PostMapping
	public ResponseEntity<Ratings> insertRating(@RequestBody Ratings ratings) {

		Ratings saveRating = ratingService.saveRating(ratings);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveRating);
	}

	@DeleteMapping("/{ratingId}")
	public ResponseEntity<?> deleteRatingByRatingId(@PathVariable String ratingId) {

		ratingService.deleteRatingByRatingId(ratingId);

		return ResponseEntity.ok().build();
	}

}
