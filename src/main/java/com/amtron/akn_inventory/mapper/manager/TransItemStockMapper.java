package com.amtron.akn_inventory.mapper.manager;

import org.springframework.stereotype.Component;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;
import com.amtron.akn_inventory.model.manager.TransItemStock;

@Component
public class TransItemStockMapper {

    public TransItemStockDto entityToDto(TransItemStock transItemStock) {
        return TransItemStockDto.builder()
                .id(transItemStock.getId())
                .itemId(transItemStock.getItem().getId())
                .itemName(transItemStock.getItem().getName())
                .categoryName(transItemStock.getItem().getCategory().getName())
                .quantity(transItemStock.getQuantity())
                .build();
    }

}
