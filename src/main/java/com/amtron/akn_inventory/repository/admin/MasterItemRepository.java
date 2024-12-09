package com.amtron.akn_inventory.repository.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amtron.akn_inventory.model.admin.MasterItem;

public interface MasterItemRepository extends JpaRepository<MasterItem, Integer> {

    Optional<MasterItem> findByName(String name);

    @Query(value = "SELECT name FROM master_item WHERE LOWER(name) LIKE LOWER(CONCAT('%', :query, '%')) LIMIT 10", nativeQuery = true)
    List<String> findByNameContainingIgnoreCase(String query);

}
