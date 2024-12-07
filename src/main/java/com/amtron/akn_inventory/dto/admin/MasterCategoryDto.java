package com.amtron.akn_inventory.dto.admin;

import com.amtron.akn_inventory.enums.Status;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MasterCategoryDto(Integer id, @NotBlank(message = "Category is required.") String name, Status status) {

}
