package com.amtron.akn_inventory.service.manager;

import java.util.List;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;

public interface ManagerService {

    List<String> searchItems(String q);

    TransItemStockDto getItemStockDetails(String itemName);

    TransItemStockDto addStock(TransItemStockDto transItemStockDto);

}
