package com.amtron.akn_inventory.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CommonService commonService;
    private final MasterCategoryService masterCategoryService;

    @Override
    public List<String> getStatusList() {
        return commonService.getStatusList();

    }

    @Override
    public void saveCategory(MasterCategoryDto masterCategoryDto) {
        masterCategoryService.saveCategory(masterCategoryDto);
    }

}
