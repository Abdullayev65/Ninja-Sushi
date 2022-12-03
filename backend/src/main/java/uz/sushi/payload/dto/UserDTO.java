package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private AddressDTO address;

    private Boolean female;

    private Boolean enabled;

    private RoleDTO role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.address = AddressDTO.mapping(user.getAddress());
        this.female = user.getFemale();
        this.enabled = user.isEnabled();
        this.role = RoleDTO.mapping(user.getRole());
    }

    public static UserDTO mapping(User user) {
        return new UserDTO(user);
    }
}
