package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    ApiResult<ComponentOfFoodDTO> add(AddComponentOfFood addComponentOfFood) {
        return service.add(addComponentOfFood);
    }

    @PutMapping(EDIT)
    ApiResult<ComponentOfFoodDTO> edit(ComponentOfFoodDTO componentOfFoodDTO) {
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
