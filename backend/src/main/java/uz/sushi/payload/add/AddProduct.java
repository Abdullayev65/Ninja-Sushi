package uz.sushi.payload.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.sushi.entity.ComponentOfFood;
import uz.sushi.entity.enums.ProductType;
import uz.sushi.payload.dto.ComponentOfFoodDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddProduct {
    private ProductType type;

    private String name;

    private List<ComponentOfFood> components;

    private String weight;

    private String price;

    private String imagUrl;
}
