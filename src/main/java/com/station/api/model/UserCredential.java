package com.station.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UserCredential {

	@Id
	@NotEmpty @Size(min=2, max=10)
	String userName;
	
	@NotEmpty @Size(min=3, max=10)
	String password;

	public UserCredential() {
	}

	public UserCredential(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
