package com.station.api.service;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;

@Validated
public interface UserService {

	List<UserProfile> getAllUserProfiles();

	UserProfile getUserProfile(@NotEmpty String userName);

	UserProfile createUserProfile(@Valid NewProfile newProfile);

	UserProfile updateUserProfile(@NotEmpty String userName, @Valid UserProfile userProfile);

	UserProfile deleteUserProfile(@NotEmpty String userName);

	User validateCredentials(@Valid UserCredential userCredential);

	User changeCredentials(@Valid ChangeCredential changeCredential);

}