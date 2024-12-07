package com.amtron.akn_inventory.service.admin;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.mapper.MasterCategoryMapper;
import com.amtron.akn_inventory.model.admin.MasterCategory;
import com.amtron.akn_inventory.repository.admin.MasterCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterCategoryServiceImpl implements MasterCategoryService {
    private final MasterCategoryRepository masterCategoryRepository;
    private final MasterCategoryMapper masterCategoryMapper;

    @Override
    public void saveCategory(MasterCategoryDto masterCategoryDto) {
        MasterCategory masterCategory = masterCategoryMapper.dtoToEntity(masterCategoryDto);
        masterCategoryRepository.save(masterCategory);

    }

}
