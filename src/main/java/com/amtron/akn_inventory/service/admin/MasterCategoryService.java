package com.amtron.akn_inventory.service.admin;

import java.util.List;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.model.admin.MasterCategory;

public interface MasterCategoryService {

    MasterCategory getById(Integer id);

    List<MasterCategoryDto> getAllCategoriesDto();

    MasterCategoryDto saveCategory(MasterCategoryDto masterCategoryDto);

    MasterCategoryDto getCategoryDtoById(Integer id);

    List<MasterCategoryDto> getAllActiveCategoriesDto();

}
