package com.example.splitwise.Repositories;

import com.example.splitwise.Models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    @Override
    Optional<UserProfile> findById(Long userId);

    @Override
    List<UserProfile> findAllById(Iterable<Long> longs);

    @Override
    UserProfile save(UserProfile userProfile);
}
