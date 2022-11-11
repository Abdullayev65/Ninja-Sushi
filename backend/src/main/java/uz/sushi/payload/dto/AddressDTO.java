package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    private Long id;
    private String street;
    private String house;
    private String entrance;
    private String building;
    private String apartment;
    private String floor;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.house = address.getHouse();
        this.entrance = address.getEntrance();
        this.building = address.getBuilding();
        this.apartment = address.getApartment();
        this.floor = address.getFloor();
    }

    public static AddressDTO mapping(Address address) {
        if (address==null)
            return null;
        return new AddressDTO(address);
    }

}
