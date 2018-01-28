package com.station.api.testutil;

import java.util.ArrayList;
import java.util.List;

import com.station.api.model.ChangeCredential;
import com.station.api.model.NewProfile;
import com.station.api.model.Product;
import com.station.api.model.User;
import com.station.api.model.UserCredential;
import com.station.api.model.UserProfile;

public class TestUtil {
	
	public UserProfile getMockUserProfile() {
		return new UserProfile("pshanmug",
				"Premkumar",
				"Shanmugam",
				"premkumar.shanmugam@email.com");
	}
	
	public NewProfile getMockNewProfile() {
		return new NewProfile(getMockUserProfile(),"secret");
	}
	
	public List<UserProfile> getMockUserProfiles() {
		List<UserProfile> mockUserProfiles = new ArrayList<UserProfile>();
		mockUserProfiles.add(new UserProfile("pshanmug",
											"Premkumar",
											"Shanmugam",
											"premkumar.shanmugam@email.com"));
		mockUserProfiles.add(new UserProfile("nnadimin",
											"Narayana",
											"Nadiminti",
											"narayana.nadiminti@email.com"));
		mockUserProfiles.add(new UserProfile("mgoyal",
											"Manu",
											"Goyal",
											"manu.goyal@email.com"));
		return mockUserProfiles;
	}
	
	public List<UserProfile> getMockEmptyUserProfiles() {
		List<UserProfile> mockEmptyUserProfiles = new ArrayList<UserProfile>();
		return mockEmptyUserProfiles;
	}
	
	public UserCredential getMockUserCredential() {
		return new UserCredential("pshanmug",
								"secret");
	}
	
	public ChangeCredential getMockChangeCredential() {
		return new ChangeCredential("pshanmug",
									"secret",
									"newsecret");
	}
	
	public User getMockUser() {
		return new User("pshanmug",
						"Premkumar",
						"Shanmugam");
	}
	
	public List<Product> getMockProducts() {
		List<Product> mockProducts = new ArrayList<Product>();
		mockProducts.add(new Product(1,
									"Leaf Lake",
									"GDN-0011",
									"March 19, 2016",
									"Leaf rake with 48-inch wooden handle.",
									"19.95",
									"3.2"));
		return mockProducts;
	}
	
	public List<Product> getMockEmptyProducts() {
		List<Product> mockEmptyProducts = new ArrayList<Product>();
		return mockEmptyProducts;
	}
}
