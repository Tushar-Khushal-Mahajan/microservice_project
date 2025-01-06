package com.ratings.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ratings.entities.Ratings;
import java.util.List;

public interface RatingRepo extends CrudRepository<Ratings, String> {

  List<Ratings> findByUserId(String userId);

  List<Ratings> findByHotelId(String hotelId);
}
