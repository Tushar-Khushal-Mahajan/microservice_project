package com.hotel.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotel.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
