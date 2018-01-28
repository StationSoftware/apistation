package com.station.api.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class NewProfile {
	
	@Valid
	private UserProfile userProfile;
	
	@NotEmpty @Size(min=3, max=10)
	private String password;
	
	public NewProfile() {
	}

	public NewProfile(UserProfile userProfile, String password) {
		this.userProfile = userProfile;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

}
