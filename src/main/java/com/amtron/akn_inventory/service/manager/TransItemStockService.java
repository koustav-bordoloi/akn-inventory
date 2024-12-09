package com.amtron.akn_inventory.service.manager;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;

public interface TransItemStockService {
    TransItemStockDto addStock(TransItemStockDto transItemStockDto);

    TransItemStockDto getItemStockDetails(String itemName);
}
