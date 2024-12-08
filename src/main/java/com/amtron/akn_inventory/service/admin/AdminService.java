package com.amtron.akn_inventory.service.admin;

import java.util.List;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.dto.admin.MasterItemDto;

public interface AdminService {

    List<String> getStatusList();

    List<MasterCategoryDto> getAllCategoriesDto();

    MasterCategoryDto getCategoryDtoById(Integer id);

    MasterCategoryDto saveCategory(MasterCategoryDto masterCategoryDto);

    List<MasterItemDto> getAllItemsDto();

    List<MasterCategoryDto> getAllActiveCategoriesDto();

    MasterItemDto saveItem(MasterItemDto masterItemDto);

    MasterItemDto getItemDtoById(Integer id);

}
