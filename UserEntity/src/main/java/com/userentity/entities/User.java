package com.userentity.entities;

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

@Entity
@Table(name = "user")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	@Id
	private String userId;
	private String userName;
	private String email;

	@Transient
	private List<Rating> ratings = new ArrayList<Rating>();
}
