package com.example.demotask.repositories;

import com.example.demotask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmailAddress(String emailAddress);

    Optional<User> getUserByEmailAddressAndPassword(String emailAddress, String password);

    List<User> findAllByPersonDeactivated(int number);
}
