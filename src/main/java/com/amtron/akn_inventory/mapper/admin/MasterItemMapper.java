package com.amtron.akn_inventory.mapper.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amtron.akn_inventory.dto.admin.MasterItemDto;
import com.amtron.akn_inventory.model.admin.MasterCategory;
import com.amtron.akn_inventory.model.admin.MasterItem;

@Component
public class MasterItemMapper {

    public MasterItem dtoToEntity(MasterItemDto masterItemDto, MasterCategory masterCategory) {

        return MasterItem.builder()
                .id(masterItemDto.id())
                .name(masterItemDto.name())
                .category(masterCategory)
                .status(masterItemDto.status())
                .build();
    }

    public List<MasterItemDto> entityListToDtoList(List<MasterItem> masterCategories) {
        return masterCategories.stream().map(masterItem -> entityToDto(masterItem)).toList();
    }

    public MasterItemDto entityToDto(MasterItem masterItem) {
        return MasterItemDto.builder()
                .id(masterItem.getId())
                .name(masterItem.getName())
                .category(masterItem.getCategory().getId())
                .categoryName(masterItem.getCategory().getName())
                .status(masterItem.getStatus())
                .build();
    }

}
