package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.dto.UserDTO;
import uz.sushi.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = UserController.BASE_PATH)
@RequiredArgsConstructor
public class UserController {
    public static final String BASE_PATH = "/api/user";
    public static final String ADD = "/";
    public static final String EDIT = "/{id}";
    public static final String GET = "/{id}";
    public static final String GET_ALL = "/all";

    private final UserService userService;

    @GetMapping(GET_ALL)
    ApiResult<List<UserDTO>> getAll() {
        return userService.getAll();
    }


}
