package com.amtron.akn_inventory.service.manager;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;
import com.amtron.akn_inventory.mapper.manager.TransItemStockMapper;
import com.amtron.akn_inventory.model.admin.MasterItem;
import com.amtron.akn_inventory.model.manager.TransItemStock;
import com.amtron.akn_inventory.repository.manager.TransItemStockRepository;
import com.amtron.akn_inventory.service.admin.MasterItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransItemStockServiceImpl implements TransItemStockService {
    private final TransItemStockMapper transItemStockMapper;
    private final TransItemStockRepository transItemStockRepository;
    private final MasterItemService masterItemService;

    @Override
    public TransItemStockDto getItemStockDetails(String itemName) {
        TransItemStock transItemStock = transItemStockRepository.findByItem_name(itemName);
        if (Objects.isNull(transItemStock)) {
            MasterItem masterItem = masterItemService.getByName(itemName);
            transItemStock = TransItemStock.builder()
                    .item(masterItem)
                    .quantity(0)
                    .build();
        }

        return transItemStockMapper.entityToDto(transItemStock);
    }

    @Override
    public TransItemStockDto addStock(TransItemStockDto transItemStockDto) {
        TransItemStock transItemStock = transItemStockRepository.findByItem_id(transItemStockDto.itemId());

        if (Objects.isNull(transItemStock)) {
            transItemStock = TransItemStock.builder()
                    .item(masterItemService.getById(transItemStockDto.itemId()))
                    .quantity(transItemStockDto.quantity())
                    .build();
        } else {
            transItemStock = transItemStock.toBuilder()
                    .quantity(transItemStock.getQuantity() + transItemStockDto.quantity()).build();
        }

        return transItemStockMapper.entityToDto(transItemStockRepository.save(transItemStock));
    }
}
