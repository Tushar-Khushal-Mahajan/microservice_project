package com.ratings.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ratings")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ratings {

  @Id
  private String ratingId;
  private String userId;
  private String hotelId;
  private int rating;
  private String feedback;
}
