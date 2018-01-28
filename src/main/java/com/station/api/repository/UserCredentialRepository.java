package com.station.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.station.api.model.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

}
