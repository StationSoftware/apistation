package com.station.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.station.api.controller.UserController;
import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;
import com.station.api.service.UserService;
import com.station.api.testutil.TestUtil;

public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;
	
	public TestUtil testUtil = new TestUtil();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserControllerList() {
				
		List<UserProfile> mockUserProfiles = testUtil.getMockUserProfiles();
		when(userService.getAllUserProfiles()).thenReturn(mockUserProfiles);
		
		ResponseEntity<List<UserProfile>> testResponseEntity
		= userController.list();
		
		verify(userService).getAllUserProfiles();
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(), is(mockUserProfiles));
	}
	
	@Test
	public void testUserControllerGet() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userService.getUserProfile(mockUserName)).thenReturn(mockUserProfile);

		ResponseEntity<UserProfile> testResponseEntity
		= userController.get(mockUserName);
		
		verify(userService).getUserProfile(mockUserName);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(), is(mockUserProfile));
	}
	
	@Test
	public void testUserControllerCreate() {
		
		NewProfile mockNewProfile = testUtil.getMockNewProfile();
		UserProfile mockUserProfile = mockNewProfile.getUserProfile();
		when(userService.createUserProfile(mockNewProfile))
			.thenReturn(mockUserProfile);
		
		ResponseEntity<UserProfile> testResponseEntity
		= userController.create(mockNewProfile);
		
		verify(userService).createUserProfile(mockNewProfile);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(), is(mockUserProfile));
	}
	
	@Test
	public void testUserControllerUpdate() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		
		when(userService.updateUserProfile(mockUserName, mockUserProfile))
			.thenReturn(mockUserProfile);
		
		ResponseEntity<UserProfile> testResponseEntity
		= userController.update(mockUserName, mockUserProfile);
		
		verify(userService).updateUserProfile(mockUserName, mockUserProfile);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(),is(mockUserProfile));		
	}
	
	@Test
	public void testUserControllerDelete() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		
		when(userService.deleteUserProfile(mockUserName))
			.thenReturn(mockUserProfile);
		
		ResponseEntity<UserProfile> testResponseEntity
		= userController.delete(mockUserName);
		
		verify(userService).deleteUserProfile(mockUserName);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(),is(mockUserProfile));
	}
	
	@Test
	public void testUserControllerValidateCredentials() {
		
		User mockUser = testUtil.getMockUser();
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		when(userService.validateCredentials(mockUserCredential))
			.thenReturn(mockUser);
		
		ResponseEntity<User> testResponseEntity
		= userController.validateCredentials(mockUserCredential);
		
		verify(userService).validateCredentials(mockUserCredential);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(),is(mockUser));
	}
	
	@Test
	public void testUserControllerChangeCredentials() {
	
		User mockUser = testUtil.getMockUser();
		ChangeCredential mockChangeCredential = testUtil.getMockChangeCredential();
		when(userService.changeCredentials(mockChangeCredential))
			.thenReturn(mockUser);
		
		ResponseEntity<User> testResponseEntity
		= userController.changeCredentials(mockChangeCredential);
		
		verify(userService).changeCredentials(mockChangeCredential);
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(),is(mockUser));
	}	
}