package com.userentity.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userentity.entities.Hotel;

@FeignClient("HOTEL")
public interface HotelClient {

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") String hotelId);
}
