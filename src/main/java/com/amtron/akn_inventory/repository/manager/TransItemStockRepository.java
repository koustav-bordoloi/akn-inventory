package com.amtron.akn_inventory.repository.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amtron.akn_inventory.model.manager.TransItemStock;

public interface TransItemStockRepository extends JpaRepository<TransItemStock, Integer> {

    TransItemStock findByItem_id(Integer itemId);

    TransItemStock findByItem_name(String itemName);

}
