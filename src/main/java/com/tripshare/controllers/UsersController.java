package com.tripshare.controllers;

import com.tripshare.util.converters.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

	@PostMapping
	public ResponseEntity<Response<Map<String, Object>>> createUser() {
		Response<Map<String, Object>> response = new Response<>();
		response.setErrorCode("404");
		response.setStatus("error");
		response.setMessage("error there");
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1);
		map.put("name", "abhi");
		response.setData(map);
		return response.entity(HttpStatus.CREATED);
	}

}
