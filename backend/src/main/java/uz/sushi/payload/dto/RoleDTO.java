package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.Role;
import uz.sushi.entity.enums.PermissionEnum;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Integer id;

    private String name;

    private String description;

    private List<PermissionEnum> permissions;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.permissions = new ArrayList<>(role.getPermissions());
    }

    public static RoleDTO mapping(Role role) {
        if (role == null)
            return null;
        return new RoleDTO(role);
    }

}
