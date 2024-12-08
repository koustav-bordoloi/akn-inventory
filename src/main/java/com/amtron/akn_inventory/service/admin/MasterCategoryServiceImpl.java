package com.amtron.akn_inventory.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.enums.Status;
import com.amtron.akn_inventory.exception.DataValidationException;
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
    public MasterCategoryDto saveCategory(MasterCategoryDto masterCategoryDto) {
        MasterCategory masterCategory = masterCategoryMapper.dtoToEntity(masterCategoryDto);
        return masterCategoryMapper.entityToDto(masterCategoryRepository.save(masterCategory));

    }

    @Override
    public List<MasterCategoryDto> getAllCategoriesDto() {
        List<MasterCategory> masterCategories = masterCategoryRepository.findAll();

        return masterCategoryMapper.entityListToDtoList(masterCategories);
    }

    @Override
    public MasterCategory getById(Integer id) {
        return masterCategoryRepository.findById(id)
                .orElseThrow(() -> new DataValidationException("Master category not found."));
    }

    @Override
    public MasterCategoryDto getCategoryDtoById(Integer id) {
        return masterCategoryMapper.entityToDto(getById(id));
    }

    @Override
    public List<MasterCategoryDto> getAllActiveCategoriesDto() {
        List<MasterCategory> masterCategories = masterCategoryRepository.findAllByStatus(Status.ACTIVE);
        return masterCategoryMapper.entityListToDtoList(masterCategories);
    }

}
