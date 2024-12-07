package com.amtron.akn_inventory.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MySecurityUser extends User {
    private String email;
    private String mobileNo;
    private String fullName;

    public MySecurityUser(String username, String password, Boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            String fullName, String email, String mobileNo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.email = email;
        this.mobileNo = mobileNo;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "MySecurityUser [fullName=" + fullName + ",email=" + email + ", mobileNo=" + mobileNo + "]";
    }

}
