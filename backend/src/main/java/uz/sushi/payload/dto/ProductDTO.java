package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import uz.sushi.entity.ComponentOfFood;
import uz.sushi.entity.Product;
import uz.sushi.exceptions.RestException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Integer id;

    private String name;

    private List<ComponentOfFoodDTO> components;

    private String weight;

    private String price;

    private String imagUrl;

    private Boolean liked;

    public ProductDTO(Product product, boolean liked) {
        this.liked = liked ? Boolean.TRUE : Boolean.FALSE;
        this.id = product.getId();
        this.name = product.getName();
        this.weight = product.getWeight();
        this.price = product.getPrice();
        this.imagUrl = product.getImagUrl();
        List<ComponentOfFood> productComponents = product.getComponents();
        if (productComponents == null || productComponents.isEmpty())
            this.components = List.of();
        else
            this.components = productComponents.stream()
                    .map(ComponentOfFoodDTO::mapping)
                    .collect(Collectors.toList());

    }

    public static ProductDTO mapping(Product product, boolean liked) {
        return new ProductDTO(product, liked);
    }

}
