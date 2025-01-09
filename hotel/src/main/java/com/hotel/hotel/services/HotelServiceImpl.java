package com.hotel.hotel.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotel.hotel.entities.Hotel;
import com.hotel.hotel.exceptions.ResourceNotFoundException;
import com.hotel.hotel.repositories.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {

	private HotelRepo repo;
	private RestTemplate template;

	public HotelServiceImpl(HotelRepo repo) {
		this.repo = repo;
		this.template = new RestTemplate();
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> allHotels = repo.findAll();

		return allHotels;
	}

	@Override
	public Hotel getHotelById(String id) {

		Hotel hotel = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with hotel ID is note found"));

//		ArrayList ratings = template.getForObject("http://RATING-SERVICE/rating/hotel/" + id, ArrayList.class);
//
//		hotel.setRating(ratings);
		return hotel;
	}

	@Override
	public Hotel addHotel(Hotel hotel) {

		hotel.setHotelId(UUID.randomUUID().toString());
		Hotel savedHotel = repo.save(hotel);
		return savedHotel;
	}

	@Override
	public void deleteHotel(String hotelId) {
		repo.deleteById(hotelId);
	}

}
