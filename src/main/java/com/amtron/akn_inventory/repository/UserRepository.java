package com.amtron.akn_inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amtron.akn_inventory.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    
}
