package com.amtron.akn_inventory.service.admin;

import java.util.List;

import com.amtron.akn_inventory.dto.admin.MasterItemDto;

public interface MasterItemService {

    List<MasterItemDto> getAllItemsDto();

    MasterItemDto getItemDtoById(Integer id);

    MasterItemDto saveItem(MasterItemDto masterItemDto);
}
