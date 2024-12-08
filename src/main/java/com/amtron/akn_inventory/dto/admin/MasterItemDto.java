package com.amtron.akn_inventory.dto.admin;

import com.amtron.akn_inventory.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MasterItemDto(Integer id, @NotBlank(message = "Item name is required.") String name,
        @NotNull(message = "Category is required.") Integer category, String categoryName, Status status) {

}
