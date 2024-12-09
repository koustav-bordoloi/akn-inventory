package com.amtron.akn_inventory.service.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;
import com.amtron.akn_inventory.service.admin.MasterItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final MasterItemService masterItemService;
    private final TransItemStockService transItemStockService;

    @Override
    public List<String> searchItems(String q) {
        return masterItemService.searchItems(q);
    }

    @Override
    public TransItemStockDto getItemStockDetails(String itemName) {
        return transItemStockService.getItemStockDetails(itemName);
    }

    @Override
    public TransItemStockDto addStock(TransItemStockDto transItemStockDto) {
        return transItemStockService.addStock(transItemStockDto);
    }

}
