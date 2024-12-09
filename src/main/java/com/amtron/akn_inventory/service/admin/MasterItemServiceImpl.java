package com.amtron.akn_inventory.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.dto.admin.MasterItemDto;
import com.amtron.akn_inventory.exception.DataValidationException;
import com.amtron.akn_inventory.mapper.admin.MasterItemMapper;
import com.amtron.akn_inventory.model.admin.MasterCategory;
import com.amtron.akn_inventory.model.admin.MasterItem;
import com.amtron.akn_inventory.repository.admin.MasterItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterItemServiceImpl implements MasterItemService {
    private final MasterCategoryService masterCategoryService;
    private final MasterItemRepository masterItemRepository;
    private final MasterItemMapper masterItemMapper;

    @Override
    public MasterItemDto saveItem(MasterItemDto masterItemDto) {
        MasterCategory masterCategory = masterCategoryService.getById(masterItemDto.category());
        MasterItem masterItem = masterItemMapper.dtoToEntity(masterItemDto, masterCategory);
        return masterItemMapper.entityToDto(masterItemRepository.save(masterItem));

    }

    @Override
    public List<MasterItemDto> getAllItemsDto() {
        List<MasterItem> masterItems = masterItemRepository.findAll();

        return masterItemMapper.entityListToDtoList(masterItems);
    }

    public MasterItem getById(Integer id) {
        return masterItemRepository.findById(id)
                .orElseThrow(() -> new DataValidationException("Master item not found."));
    }

    @Override
    public MasterItem getByName(String itemName) {
        return masterItemRepository.findByName(itemName)
                .orElseThrow(() -> new DataValidationException("Master item not found."));
    }

    @Override
    public MasterItemDto getItemDtoById(Integer id) {
        return masterItemMapper.entityToDto(getById(id));
    }

    @Override
    public List<String> searchItems(String query) {
        return masterItemRepository.findByNameContainingIgnoreCase(query);
    }

}
