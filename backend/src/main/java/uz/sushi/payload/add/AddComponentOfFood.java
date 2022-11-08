package uz.sushi.payload.add;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.ComponentOfFood;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddComponentOfFood {

    private String name;

    private String imageUrl;

    public ComponentOfFood getComponent() {
        return new ComponentOfFood(null,name,imageUrl);
    }

}
