package com.hotel.hotel.controller;

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

import com.hotel.hotel.entities.Hotel;
import com.hotel.hotel.services.HotelServiceImpl;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	private HotelServiceImpl hotelService;

	public HotelController(HotelServiceImpl hotelService) {
		this.hotelService = hotelService;
	}

	// -------------------

	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels() {
		hotelService.getAllHotels();

		return ResponseEntity.ok(hotelService.getAllHotels());
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
		return ResponseEntity.ok(hotelService.getHotelById(hotelId));
	}

	@PostMapping
	public ResponseEntity<Hotel> insertHotel(@RequestBody Hotel hotel) {

		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
	}

	@DeleteMapping("/{hotelId}")
	public ResponseEntity<?> deleteHotel(@PathVariable String hotelId) {

		hotelService.deleteHotel(hotelId);
		return ResponseEntity.ok().body("Hotel Deleted");
	}

}
