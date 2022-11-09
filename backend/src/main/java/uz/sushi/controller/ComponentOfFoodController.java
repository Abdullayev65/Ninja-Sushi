package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import uz.sushi.entity.User;
import uz.sushi.oap.CurrentUser;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.add.AddComponentOfFood;
import uz.sushi.payload.dto.ComponentOfFoodDTO;
import uz.sushi.service.ComponentOfFoodService;

import java.util.List;

@RestController
@RequestMapping(path = ComponentOfFoodController.BASE_PATH)
@RequiredArgsConstructor
public class ComponentOfFoodController {
    public static final String BASE_PATH = "/api/component";
    public static final String ADD = "/";
    public static final String EDIT = "/";
    public static final String GET = "/{id}";
    public static final String GET_ALL = "/all";

    private final ComponentOfFoodService service;

    @PostMapping(ADD)
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    ApiResult<ComponentOfFoodDTO> add(@RequestBody AddComponentOfFood addComponentOfFood) {
        return service.add(addComponentOfFood);
    }

    @PutMapping(EDIT)
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    ApiResult<ComponentOfFoodDTO> edit(@RequestBody ComponentOfFoodDTO componentOfFoodDTO) {
        return service.edit(componentOfFoodDTO);
    }

    @GetMapping(GET)
    ApiResult<ComponentOfFoodDTO> get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping(GET_ALL)
    ApiResult<List<ComponentOfFoodDTO>> getAll() {
        return service.getAll();
    }

}
