package uz.sushi.payload.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameAndImageUrlDTO {

    private String name;

    private String imageUrl;

    public static NameAndImageUrlDTO mapping(Product product) {
        return new NameAndImageUrlDTO(product.getName(), product.getImagUrl());
    }
}
