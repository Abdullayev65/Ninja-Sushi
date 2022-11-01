package uz.sushi.payload.dto;

import java.util.List;

public class ProductDTO {

    private Integer id;

    private String name;

    private List<ComponentOfFoodDTO> components;

    private String weight;

    private String price;

    private String imagUrl;

    public Boolean liked;

}
