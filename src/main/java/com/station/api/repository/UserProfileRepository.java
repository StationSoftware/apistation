package com.station.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.station.api.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

}