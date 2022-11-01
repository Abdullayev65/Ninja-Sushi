package uz.sushi.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.sushi.entity.ComponentOfFood;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComponentOfFoodDTO {
    private Integer id;

    private String name;

    private String imageUrl;

    public static ComponentOfFoodDTO mapping(ComponentOfFood component) {
        return new ComponentOfFoodDTO(component.getId(),component.getName(),component.getImageUrl());
    }

    public ComponentOfFood getComponent() {
        return new ComponentOfFood(id,name,imageUrl);
    }
}
