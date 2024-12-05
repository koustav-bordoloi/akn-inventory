package com.amtron.akn_inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN("ROLE_ADMIN", "/admin"),
    ROLE_MANAGER("ROLE_MANAGER", "/manager"),
    MULTIPLE_ROLE("MULTIPLE", "/select-role");

    private final String roleName;
    private final String targetUrl;

}
