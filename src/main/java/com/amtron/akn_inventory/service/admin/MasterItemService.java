package com.amtron.akn_inventory.service.admin;

import java.util.List;

import com.amtron.akn_inventory.dto.admin.MasterItemDto;
import com.amtron.akn_inventory.model.admin.MasterItem;

public interface MasterItemService {

    MasterItem getById(Integer itemId);

    MasterItem getByName(String itemName);

    List<MasterItemDto> getAllItemsDto();

    MasterItemDto getItemDtoById(Integer id);

    MasterItemDto saveItem(MasterItemDto masterItemDto);

    List<String> searchItems(String q);

}
