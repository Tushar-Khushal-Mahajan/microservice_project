package com.userentity.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userentity.entities.Hotel;
import com.userentity.entities.Rating;
import com.userentity.entities.User;
import com.userentity.exceptions.ResourceNotFoundExceptions;
import com.userentity.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	private UserRepo repo;
	private RestTemplate template;

	public UserServiceImpl(UserRepo repo, RestTemplate template) {
		super();
		this.repo = repo;

		this.template = template;
	}

	@Override
	public List<User> getAllUsers() {

		List<User> allUsers = repo.findAll();

		List<User> completeUser = allUsers.stream().map(user -> {

			// GET ALL RATINGS OF SPECIFIC USER
			Rating[] ListOfRatings = template.getForObject("http://localhost:8083/rating/user/" + user.getUserId(),
					Rating[].class);
			List<Rating> userRatings = List.of(ListOfRatings);

			// PLACE HOTEL OBJECT INSIDE EACH RATING
			List<Rating> ratings = userRatings.stream().map(rating -> {
				String hotelId = rating.getHotelId();

				// GET HOTEL BY HOTEL ID
				ResponseEntity<Hotel> hotel = template.getForEntity("http://localhost:8082/hotel/" + hotelId,
						Hotel.class);

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
		Rating[] ListOfRatings = template.getForObject("http://localhost:8083/rating/user/" + userId, Rating[].class);
		List<Rating> userRatings = List.of(ListOfRatings);

		List<Rating> ratings = userRatings.stream().map(rating -> {
			String hotelId = rating.getHotelId();

			// GET HOTEL BY HOTEL ID
			ResponseEntity<Hotel> hotel = template.getForEntity("http://localhost:8082/hotel/" + hotelId, Hotel.class);

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
