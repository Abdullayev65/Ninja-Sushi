package uz.sushi.payload.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.sushi.entity.enums.ProductType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddProduct {
    private ProductType type;

    private String name;

    private List<Integer> components;

    private String weight;

    private String price;

    private String imagUrl;
}
