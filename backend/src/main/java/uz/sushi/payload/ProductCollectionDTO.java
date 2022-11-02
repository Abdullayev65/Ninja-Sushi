package uz.sushi.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.payload.dto.ProductDTO;
import uz.sushi.payload.dto.SetDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCollectionDTO {
    private List<ProductDTO> rolls;
    private List<ProductDTO> sushi;
    private List<SetDTO> sets;
    private List<ProductDTO> snacks;
    private List<ProductDTO> drinks;

//    public boolean add(ProductDTO productDTO, ProductType type) {
//        switch (type) {
//            case SET:
//                if (sets == null) sets = new ArrayList<>();
//                return sets.add(productDTO);
//            case SUSHI:
//                if (sushi == null) sushi = new ArrayList<>();
//                return sushi.add(productDTO);
//            case ROLL:
//                if (rolls == null) rolls = new ArrayList<>();
//                return rolls.add(productDTO);
//            case SNACKS:
//                if (snacks == null) snacks = new ArrayList<>();
//                return snacks.add(productDTO);
//            case DRINK:
//                if (drinks == null) drinks = new ArrayList<>();
//                return drinks.add(productDTO);
//        }
//        return false;
//    }

}
