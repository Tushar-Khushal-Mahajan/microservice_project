package com.hotel.hotel.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "hotel")
public class Hotel {
	@Id
	private String HotelId;
	private String Hotelname;
	private String address;

	@Transient
	private List<Rating> rating = new ArrayList<Rating>();
}
