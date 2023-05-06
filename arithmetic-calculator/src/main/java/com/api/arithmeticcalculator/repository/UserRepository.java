package com.api.arithmeticcalculator.repository;

import com.api.arithmeticcalculator.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> getUserByUserName(String userName);
}
