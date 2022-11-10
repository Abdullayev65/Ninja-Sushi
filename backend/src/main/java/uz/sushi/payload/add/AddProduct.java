package uz.sushi.payload.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import uz.sushi.entity.Product;
import uz.sushi.entity.enums.ProductType;
import uz.sushi.exceptions.RestException;

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

    public void setFieldsToEntity(Product product) {
        product.setType(type);
        product.setName(name);
        product.setWeight(weight);
        product.setImagUrl(imagUrl);
        product.setPrice(price);
        if (components.size() != product.getComponents().size()) {
            throw RestException.restThrow("TODO in ProductDTO", HttpStatus.NOT_MODIFIED);
        }
    }

}
