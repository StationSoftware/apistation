package com.station.api.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;
import com.station.api.repository.UserCredentialRepository;
import com.station.api.repository.UserProfileRepository;
import com.station.api.service.UserServiceImpl;
import com.station.api.testutil.TestUtil;

public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserCredentialRepository userCredentialRepository;
	
	@Mock
	private UserProfileRepository userProfileRepository;
		
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private TestUtil testUtil = new TestUtil();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserService_getAllUserProfiles_ReturningProfiles() {
				
		List<UserProfile> mockUserProfiles = testUtil.getMockUserProfiles();
		when(userProfileRepository.findAll()).thenReturn(mockUserProfiles);
		
		List<UserProfile> testUserProfiles
		= userService.getAllUserProfiles();
		
		verify(userProfileRepository).findAll();
		assertThat(testUserProfiles,is(mockUserProfiles));
	}
	
	@Test
	public void testUserService_getAllUserProfiles_EmptyProfiles() {
				
		List<UserProfile> mockEmptyUserProfiles = testUtil.getMockEmptyUserProfiles();
		when(userProfileRepository.findAll()).thenReturn(mockEmptyUserProfiles);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("No user profiles");
		
		userService.getAllUserProfiles();
	}
	
	@Test
	public void testUserService_getUserProfile_ReturningProfile() {
				
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);
		
		UserProfile testUserProfile
		= userService.getUserProfile(mockUserName);
		
		verify(userProfileRepository).findOne(mockUserName);
		assertThat(testUserProfile,is(mockUserProfile));
	}
	
	@Test
	public void testUserService_getUserProfile_NoUser() {
		
		String mockUserName = "doesnotexist";
		when(userProfileRepository.findOne(mockUserName)).thenReturn(null);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("User does not exist");
		
		userService.getUserProfile(mockUserName);
	}
	
	@Test
	public void testUserService_createUserProfile_ReturningProfile() {
		
		NewProfile mockNewProfile = testUtil.getMockNewProfile();
		UserProfile mockUserProfile = mockNewProfile.getUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(null);
		when(userProfileRepository.saveAndFlush(mockUserProfile)).thenReturn(mockUserProfile);
		
		UserProfile testUserProfile
		= userService.createUserProfile(mockNewProfile);
		
		verify(userProfileRepository).findOne(mockUserName);
		verify(userProfileRepository).saveAndFlush(mockUserProfile);
		assertThat(testUserProfile,is(mockUserProfile));
	}
	
	@Test
	public void testUserService_createUserProfile_DuplicateUser() {
		
		NewProfile mockNewProfile = testUtil.getMockNewProfile();
		UserProfile mockUserProfile = mockNewProfile.getUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);

		thrown.expect(ValidationException.class);
		thrown.expectMessage("Duplicate user");
				
		userService.createUserProfile(mockNewProfile);
	}
	
	@Test
	public void testUserService_updateUserProfile_ReturningProfile() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);
		when(userProfileRepository.saveAndFlush(mockUserProfile)).thenReturn(mockUserProfile);
		
		UserProfile testUserProfile
		= userService.updateUserProfile(mockUserName, mockUserProfile);
		
		verify(userProfileRepository).findOne(mockUserName);
		verify(userProfileRepository).saveAndFlush(mockUserProfile);
		assertThat(testUserProfile,is(mockUserProfile));
	}
	
	@Test
	public void testUserService_updateUserProfile_NoUser() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(null);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("User does not exist");
		
		userService.updateUserProfile(mockUserName, mockUserProfile);
	}
	
	@Test
	public void testUserService_deleteUserProfile_ReturningProfile() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(mockUserCredential);
		
		UserProfile testUserProfile
		= userService.deleteUserProfile(mockUserName);
		
		verify(userProfileRepository).findOne(mockUserName);
		verify(userProfileRepository).delete(mockUserProfile);
		verify(userCredentialRepository).findOne(mockUserName);
		verify(userCredentialRepository).delete(mockUserCredential);
		assertThat(testUserProfile,is(mockUserProfile));
	}
	
	@Test
	public void testUserService_deleteUserProfile_NoUser() {
		
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		String mockUserName = mockUserProfile.getUserName();
		when(userProfileRepository.findOne(mockUserName)).thenReturn(null);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("User does not exist");
		
		userService.deleteUserProfile(mockUserName);
	}
	
	@Test
	public void testUserService_validateCredentials_ReturningUser() {
		
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		String mockUserName = mockUserCredential.getUserName();
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		User mockUser = testUtil.getMockUser();
		
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(mockUserCredential);
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);
		
		User testUser = userService.validateCredentials(mockUserCredential);
		
		verify(userCredentialRepository).findOne(mockUserName);
		verify(userProfileRepository).findOne(mockUserName);
		assertThat(testUser.getUserName(), is(mockUser.getUserName()));
		assertThat(testUser.getFirstName(), is(mockUser.getFirstName()));
		assertThat(testUser.getLastName(), is(mockUser.getLastName()));
	}
	
	@Test
	public void testUserService_validateCredentials_NoUser() {
		
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		String mockUserName = mockUserCredential.getUserName();
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(null);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("User does not exist");
		
		userService.validateCredentials(mockUserCredential);
	}
	
	@Test
	public void testUserService_validateCredentials_IncorrectPassword() {
		
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		UserCredential mockUserCredential2 = testUtil.getMockUserCredential();
		mockUserCredential2.setPassword("wrongsecret");
		
		String mockUserName = mockUserCredential.getUserName();
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(mockUserCredential2);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("Incorrect password");
		
		userService.validateCredentials(mockUserCredential);
	}
	
	@Test
	public void testUserService_changeCredentials_ReturningUser() {
		
		ChangeCredential mockChangeCredential = testUtil.getMockChangeCredential();
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		String mockUserName = mockUserCredential.getUserName();
		UserProfile mockUserProfile = testUtil.getMockUserProfile();
		User mockUser = testUtil.getMockUser();
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(mockUserCredential);
		when(userProfileRepository.findOne(mockUserName)).thenReturn(mockUserProfile);
		
		User testUser = userService.changeCredentials(mockChangeCredential);
		
		verify(userCredentialRepository).findOne(mockUserName);
		verify(userCredentialRepository).saveAndFlush(mockUserCredential);
		verify(userProfileRepository).findOne(mockUserName);
		assertThat(testUser.getUserName(), is(mockUser.getUserName()));
		assertThat(testUser.getFirstName(), is(mockUser.getFirstName()));
		assertThat(testUser.getLastName(), is(mockUser.getLastName()));
	}
	
	@Test
	public void testUserService_changeCredentials_NoUser() {
		
		ChangeCredential mockChangeCredential = testUtil.getMockChangeCredential();
		String mockUserName = mockChangeCredential.getUserName();
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(null);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("User does not exist");
		
		userService.changeCredentials(mockChangeCredential);
	}
	
	@Test
	public void testUserService_changeCredentials_IncorrectPassword() {
		
		ChangeCredential mockChangeCredential = testUtil.getMockChangeCredential();
		mockChangeCredential.setOldPassword("wrongsecret");
		UserCredential mockUserCredential = testUtil.getMockUserCredential();
		String mockUserName = mockUserCredential.getUserName();
		
		when(userCredentialRepository.findOne(mockUserName)).thenReturn(mockUserCredential);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("Incorrect old password");
		
		userService.changeCredentials(mockChangeCredential);
	}
}