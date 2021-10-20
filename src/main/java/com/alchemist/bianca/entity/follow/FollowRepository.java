package com.alchemist.bianca.entity.follow;

import com.alchemist.bianca.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
    List<User> findAllByFollower(String email);
    List<User> findAllByFollowing(String email);
}
