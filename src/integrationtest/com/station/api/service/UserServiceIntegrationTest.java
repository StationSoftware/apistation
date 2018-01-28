package com.station.api.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.station.api.model.UserProfile;
import com.station.api.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Test @Ignore
	public void testUserService_getAllUserProfiles() {
		List<UserProfile> testProfiles = userService.getAllUserProfiles();
		assertThat(testProfiles.size(), is(equalTo(3)));

	}

}
