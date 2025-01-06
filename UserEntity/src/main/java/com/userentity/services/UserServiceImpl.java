package com.userentity.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userentity.entities.Hotel;
import com.userentity.entities.Rating;
import com.userentity.entities.User;
import com.userentity.exceptions.ResourceNotFoundExceptions;
import com.userentity.feignClient.HotelClient;
import com.userentity.feignClient.RatingClient;
import com.userentity.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo repo;
	private HotelClient hotelClient;
	private RatingClient ratingClient;

	public UserServiceImpl(UserRepo repo, HotelClient hotelClient, RatingClient ratingClient) {
		this.repo = repo;
		this.hotelClient = hotelClient;
		this.ratingClient = ratingClient;
	}

	@Override
	public List<User> getAllUsers() {

		List<User> allUsers = repo.findAll();

		List<User> completeUser = allUsers.stream().map(user -> {

			// GET ALL RATINGS OF SPECIFIC USER
			ResponseEntity<List<Rating>> ratingsEntity = ratingClient.getRatingsByUserId(user.getUserId());
			List<Rating> userRatings = ratingsEntity.getBody();

			// PLACE HOTEL OBJECT INSIDE EACH RATING
			List<Rating> ratings = userRatings.stream().map(rating -> {
				String hotelId = rating.getHotelId();

				// GET HOTEL BY HOTEL ID
				ResponseEntity<Hotel> hotel = hotelClient.getHotelById(hotelId);

				rating.setHotel(hotel.getBody());
				return rating;
			}).collect(Collectors.toList());
			// RATING OBJECT COLLECT

			user.setRatings(ratings);

			return user;
		}).collect(Collectors.toList());

		return completeUser;
	}

	@Override
	public User getUserById(String userId) {
		User user = repo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user with userId not fund"));

		// GET ALL RATINGS OF USER BY USER_ID
		ResponseEntity<List<Rating>> ratingsByUserId = ratingClient.getRatingsByUserId(userId);
		List<Rating> userRatings = ratingsByUserId.getBody();

		List<Rating> ratings = userRatings.stream().map(rating -> {
			String hotelId = rating.getHotelId();

			// GET HOTEL BY HOTEL ID
			ResponseEntity<Hotel> hotel = hotelClient.getHotelById(hotelId);

			rating.setHotel(hotel.getBody());
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratings);

		return user;
	}

	@Override
	public User InsertUser(User user) {

		user.setUserId(UUID.randomUUID().toString());
		User savedUser = repo.save(user);

		return savedUser;
	}

}
