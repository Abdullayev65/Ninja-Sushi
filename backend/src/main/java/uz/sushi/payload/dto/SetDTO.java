package uz.sushi.payload.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.SetOfProduct;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetDTO {

    private Integer id;

    private String name;

    private List<NameAndImageUrlDTO> components;

    private String weight;

    private String price;

    private String imagUrl;

    public Boolean liked;

    public SetDTO(SetOfProduct setOfProduct, boolean liked) {
        if (liked)
            this.liked=Boolean.TRUE;
        this.id = setOfProduct.getId();
        this.name = setOfProduct.getName();
        this.weight = setOfProduct.getWeight();
        this.price = setOfProduct.getPrice();
        this.imagUrl = setOfProduct.getImagUrl();
        this.components = setOfProduct.getProducts()
                .stream().map(NameAndImageUrlDTO::mapping)
                .collect(Collectors.toList());
    }

    public static SetDTO mapping(SetOfProduct setOfProduct, boolean liked) {
        return new SetDTO(setOfProduct, liked);
    }
}
