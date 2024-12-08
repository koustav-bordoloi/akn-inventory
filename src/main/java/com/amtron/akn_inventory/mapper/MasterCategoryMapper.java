package com.amtron.akn_inventory.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.model.admin.MasterCategory;

@Component
public class MasterCategoryMapper {

    public MasterCategory dtoToEntity(MasterCategoryDto masterCategoryDto) {

        return MasterCategory.builder()
                .id(masterCategoryDto.id())
                .name(masterCategoryDto.name())
                .status(masterCategoryDto.status())
                .build();
    }

    public List<MasterCategoryDto> entityListToDtoList(List<MasterCategory> masterCategories) {
        return masterCategories.stream().map(masterCategory -> entityToDto(masterCategory)).toList();
    }

    public MasterCategoryDto entityToDto(MasterCategory masterCategory) {
        return MasterCategoryDto.builder()
                .id(masterCategory.getId())
                .name(masterCategory.getName())
                .status(masterCategory.getStatus())
                .build();
    }

}
