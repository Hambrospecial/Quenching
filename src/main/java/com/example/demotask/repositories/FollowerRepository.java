package com.example.demotask.repositories;

import com.example.demotask.entities.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Long> {
    List<Followers> findByFollowerIdAndAndFolloweeId(Long followerId, Long followeeId);

    List<Followers> findAllByFollowerId(Long followerId);

    List<Followers> findAllByFolloweeId(Long followeeId);
}
