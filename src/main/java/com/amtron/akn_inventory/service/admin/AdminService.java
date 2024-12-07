package com.amtron.akn_inventory.service.admin;

import java.util.List;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;

public interface AdminService {

    List<String> getStatusList();

    void saveCategory(MasterCategoryDto masterCategoryDto);

}
