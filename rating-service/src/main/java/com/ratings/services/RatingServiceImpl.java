package com.ratings.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ratings.entities.Ratings;
import com.ratings.repositories.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {

  private RatingRepo repo;

  public RatingServiceImpl(RatingRepo repo) {
    this.repo = repo;
  }

  // ---------------------
  @Override
  public List<Ratings> getAllRatings() {
    List<Ratings> all = (List<Ratings>) repo.findAll();
    return all;
  }

  @Override
  public List<Ratings> getRatingsByUserId(String userId) {
    List<Ratings> ratings = repo.findByUserId(userId);

    return ratings;
  }

  @Override
  public List<Ratings> getRatingsByHotelId(String hotelId) {
    List<Ratings> ratings = repo.findByHotelId(hotelId);

    return ratings;
  }

  @Override
  public Ratings saveRating(Ratings rating) {

    rating.setRatingId(UUID.randomUUID().toString());
    Ratings savedRating = repo.save(rating);

    return savedRating;
  }

  @Override
  public void deleteRatingByRatingId(String ratingId) {

    repo.deleteById(ratingId);
  }

}
