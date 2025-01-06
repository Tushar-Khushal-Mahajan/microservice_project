package com.ratings.services;

import java.util.List;

import com.ratings.entities.Ratings;

public interface RatingService {

  List<Ratings> getAllRatings();

  List<Ratings> getRatingsByUserId(String userId);

  List<Ratings> getRatingsByHotelId(String hotelId);

  Ratings saveRating(Ratings rating);

  void deleteRatingByRatingId(String ratingId);
}
