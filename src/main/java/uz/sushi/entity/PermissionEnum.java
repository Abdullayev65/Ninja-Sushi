package uz.sushi.entity;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {

    ORDER,
    EDIT_PRODUCT;
    @Override
    public String getAuthority() {
        return this.name();
    }
}
