package com.amtron.akn_inventory.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amtron.akn_inventory.enums.Status;
import com.amtron.akn_inventory.model.admin.MasterCategory;

public interface MasterCategoryRepository extends JpaRepository<MasterCategory, Integer> {

    List<MasterCategory> findAllByStatus(Status active);

}
