package com.userentity.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userentity.entities.Rating;

@FeignClient("RATING-SERVICE")
public interface RatingClient {

	@GetMapping("rating/user/{userId}")
	ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") String userId);
}
