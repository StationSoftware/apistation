package com.station.api.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangeCredential {

	@NotEmpty @Size(min=2, max=10)
	String userName;
	
	@NotEmpty
	String oldPassword;

	@NotEmpty @Size(min=3, max=10)
	String newPassword;

	public ChangeCredential(String userName, String oldPassword, String newPassword) {
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
