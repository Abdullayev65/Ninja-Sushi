package uz.sushi.payload.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.sushi.entity.ComponentOfFood;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddComponentOfFood {

    private String name;

    private String imageUrl;

    public ComponentOfFood getComponent() {
        return new ComponentOfFood(null,name,imageUrl);
    }

}
