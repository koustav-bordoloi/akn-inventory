package com.amtron.akn_inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.amtron.akn_inventory.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "roles" })
    Optional<User> findByUsername(String username);

    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "roles" })
    Optional<User> findByEmail(String email);

    @EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "roles" })
    Optional<User> findByMobileNo(String mobileNo);

}
