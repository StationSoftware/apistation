package com.station.api.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;
import com.station.api.repository.UserCredentialRepository;
import com.station.api.repository.UserProfileRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@Override
	public List<UserProfile> getAllUserProfiles() {

		List<UserProfile> userProfiles = userProfileRepository.findAll();

		if (userProfiles.isEmpty()) {
			throw new ValidationException("No user profiles");
		} else {
			return userProfiles;
		}
	}

	@Override
	public UserProfile getUserProfile(String userName) {
		UserProfile userProfile = userProfileRepository.findOne(userName);
		if (userProfile == null) {
			throw new ValidationException("User does not exist");
		} else {
			return userProfile;
		}
	}

	@Override
	public UserProfile createUserProfile(NewProfile newProfile) {

		String userName = newProfile.getUserProfile().getUserName();
		UserProfile checkProfile = userProfileRepository.findOne(userName);

		if (checkProfile != null) {
			throw new ValidationException("Duplicate user");
		}

		UserCredential userCredential = new UserCredential(userName, newProfile.getPassword());
		userCredentialRepository.saveAndFlush(userCredential);

		UserProfile userProfile = userProfileRepository.saveAndFlush(newProfile.getUserProfile());
		return userProfile;
	}

	@Override
	public UserProfile updateUserProfile(String userName, UserProfile userProfile) {
		UserProfile existingUserProfile = userProfileRepository.findOne(userName);
		if (existingUserProfile == null) {
			throw new ValidationException("User does not exist");
		} else {
			BeanUtils.copyProperties(userProfile, existingUserProfile);
			return userProfileRepository.saveAndFlush(existingUserProfile);
		}
	}

	@Override
	public UserProfile deleteUserProfile(String userName) {
		UserProfile existingUserProfile = userProfileRepository.findOne(userName);

		if (existingUserProfile == null) {
			throw new ValidationException("User does not exist");
		}

		userProfileRepository.delete(existingUserProfile);
		UserCredential existingUserLogin = userCredentialRepository.findOne(userName);
		userCredentialRepository.delete(existingUserLogin);

		return existingUserProfile;
	}

	@Override
	public User validateCredentials(UserCredential userCredential) {
		UserCredential dbUserCredential = userCredentialRepository.findOne(userCredential.getUserName());

		if (dbUserCredential == null) {
			throw new ValidationException("User does not exist");
		}
		if (dbUserCredential.getPassword().equals(userCredential.getPassword())) {
			return getUser(getUserProfile(userCredential.getUserName()));
		} else {
			throw new ValidationException("Incorrect password");
		}
	}

	@Override
	public User changeCredentials(ChangeCredential changeCredential) {
		UserCredential existingCredential = userCredentialRepository.findOne(changeCredential.getUserName());
		
		if (existingCredential == null) {
			throw new ValidationException("User does not exist");
		}
		if (existingCredential.getPassword().equals(changeCredential.getOldPassword())) {
			existingCredential.setPassword(changeCredential.getNewPassword());
			userCredentialRepository.saveAndFlush(existingCredential);
			return getUser(getUserProfile(changeCredential.getUserName()));
		} else {
			throw new ValidationException("Incorrect old password");
		}
	}
	
	private User getUser(UserProfile userProfile) {
		User user = new User(userProfile.getUserName(),
							 userProfile.getFirstName(),
							 userProfile.getLastName());
		return user;
	}

}
