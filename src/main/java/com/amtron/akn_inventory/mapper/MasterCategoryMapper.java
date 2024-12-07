package com.amtron.akn_inventory.mapper;

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

}
