package com.inventoryservice.config;
import com.inventoryservice.model.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;

public class UserInfoValidator {
    public static List<SimpleGrantedAuthority> validateUserInfo(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String roleName = role.substring(role.lastIndexOf("_") + 1, role.length() - 2);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
        authorities.add(authority);
        return authorities;
    }
}
