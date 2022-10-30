package uz.sushi.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import uz.sushi.entity.Role;

@Component
public class Beans {
    public final static Role userRole = new Role();

    @Bean
    public Role userRole() {
        return userRole;
    }

}
