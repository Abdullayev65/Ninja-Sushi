package uz.sushi.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {

    SUPER_ADMIN,
    ORDER,
    EDIT_PRODUCT;
    @Override
    public String getAuthority() {
        return this.name();
    }
}
