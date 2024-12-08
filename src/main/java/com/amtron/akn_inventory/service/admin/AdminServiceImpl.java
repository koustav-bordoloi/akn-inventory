package com.amtron.akn_inventory.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.dto.admin.MasterItemDto;
import com.amtron.akn_inventory.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CommonService commonService;
    private final MasterCategoryService masterCategoryService;
    private final MasterItemService masterItemService;

    @Override
    public List<String> getStatusList() {
        return commonService.getStatusList();

    }

    @Override
    public List<MasterCategoryDto> getAllCategoriesDto() {
        return masterCategoryService.getAllCategoriesDto();
    }

    @Override
    public MasterCategoryDto getCategoryDtoById(Integer id) {
        return masterCategoryService.getCategoryDtoById(id);
    }

    @Override
    public MasterCategoryDto saveCategory(MasterCategoryDto masterCategoryDto) {
        return masterCategoryService.saveCategory(masterCategoryDto);
    }

    @Override
    public List<MasterCategoryDto> getAllActiveCategoriesDto() {
        return masterCategoryService.getAllActiveCategoriesDto();
    }

    @Override
    public List<MasterItemDto> getAllItemsDto() {
        return masterItemService.getAllItemsDto();
    }

    @Override
    public MasterItemDto getItemDtoById(Integer id) {
        return masterItemService.getItemDtoById(id);
    }

    @Override
    public MasterItemDto saveItem(MasterItemDto masterItemDto) {
        return masterItemService.saveItem(masterItemDto);
    }

}
