package com.station.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;
import com.station.api.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profiles")
	public ResponseEntity<List<UserProfile>> list() {
		return ResponseEntity.ok(userService.getAllUserProfiles());
	}
	
	@GetMapping("/profiles/{userName}")
	public ResponseEntity<UserProfile> get(@PathVariable String userName) {
		return ResponseEntity.ok(userService.getUserProfile(userName));
	}
	
	@PostMapping("/profiles")
	public ResponseEntity<UserProfile> create(@RequestBody NewProfile newProfile) {
		return ResponseEntity.ok(userService.createUserProfile(newProfile));
	}
	
	@PutMapping("/profiles/{userName}")
	public ResponseEntity<UserProfile> update(@PathVariable String userName, @RequestBody UserProfile userProfile) {
		return ResponseEntity.ok(userService.updateUserProfile(userName, userProfile));
	}

	@DeleteMapping("/profiles/{userName}")
	public ResponseEntity<UserProfile> delete(@PathVariable String userName) {
		return ResponseEntity.ok(userService.deleteUserProfile(userName));
	}
	
	@PostMapping("/credentials/validate")
	public ResponseEntity<User> validateCredentials(@RequestBody UserCredential userCredential) {
		return ResponseEntity.ok(userService.validateCredentials(userCredential));
	}
	
	@PutMapping("/credentials/change")
	public ResponseEntity<User> changeCredentials(@RequestBody ChangeCredential changeCredential) {
		return ResponseEntity.ok(userService.changeCredentials(changeCredential));
	}

}
