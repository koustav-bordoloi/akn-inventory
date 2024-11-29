package com.amtron.akn_inventory.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {

    private Object data;
    private String msg;
    private Long recordsTotal;
    private Long recordsFiltered;
    private int page;
    private int pageSize;

}
