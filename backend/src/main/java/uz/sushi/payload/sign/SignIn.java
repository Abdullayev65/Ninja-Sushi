package uz.sushi.payload.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignIn {
    private String token;
    private Boolean admin;

    public static SignIn user(String token) {
        return new SignIn(token, null);
    }

    public static SignIn admin(String token) {
        return new SignIn(token, true);
    }

    public SignIn(String token, Boolean admin) {
        this.token = token;
        if (admin != null && admin)
            this.admin = Boolean.TRUE;
    }

}
