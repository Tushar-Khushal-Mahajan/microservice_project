package com.hotel.hotel.services;

import java.util.List;

import com.hotel.hotel.entities.Hotel;

public interface HotelService {

  public List<Hotel> getAllHotels();

  public Hotel getHotelById(String id);

  public Hotel addHotel(Hotel hotel);

  public void deleteHotel(String hotelId);
}
