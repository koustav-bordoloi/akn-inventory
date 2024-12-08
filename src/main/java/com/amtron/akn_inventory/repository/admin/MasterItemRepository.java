package com.amtron.akn_inventory.repository.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amtron.akn_inventory.model.admin.MasterItem;

public interface MasterItemRepository extends JpaRepository<MasterItem, Integer> {

    Optional<MasterItem> findByName(String name);

}
