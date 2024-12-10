package com.amtron.akn_inventory.dto.manager;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TransItemStockDto(
		Integer id,
		Integer itemId,
		String itemName,
		String categoryName,

		@NotNull(message = "Quantity is required.") //
		@Positive(message = "Quntity is invalid.") //
		Integer quantity) {

}
