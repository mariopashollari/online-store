package com.individual.online_store.repositories;

import com.individual.online_store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select u from User u where upper(u.email) like concat('%', upper(:email), '%')")
    Optional<User> findByEmail(@Param("email") String email);

}
