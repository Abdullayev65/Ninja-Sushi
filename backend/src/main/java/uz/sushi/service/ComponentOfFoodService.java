package uz.sushi.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.sushi.entity.ComponentOfFood;
import uz.sushi.exceptions.RestException;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.add.AddComponentOfFood;
import uz.sushi.payload.dto.ComponentOfFoodDTO;
import uz.sushi.repository.ComponentOfFoodRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComponentOfFoodService {
    private final ComponentOfFoodRepository repository;
    public ApiResult<ComponentOfFoodDTO> add(AddComponentOfFood addComponentOfFood) {
        if (repository.existsByName(addComponentOfFood.getName()))
            throw RestException.restThrow("this name alrady used", HttpStatus.BAD_REQUEST);
        ComponentOfFood component = repository.save(addComponentOfFood.getComponent());

        return ApiResult.successResponse(ComponentOfFoodDTO.mapping(component));
    }

    public ApiResult<ComponentOfFoodDTO> get(Integer id) {
        Optional<ComponentOfFood> optional;
        if ((optional = repository.findById(id)).isEmpty())
            throw RestException.restThrow("this name alrady used", HttpStatus.BAD_REQUEST);
        ComponentOfFood component = repository.save(optional.get());

        return ApiResult.successResponse(ComponentOfFoodDTO.mapping(component));
    }

    public ApiResult<List<ComponentOfFoodDTO>> getAll() {
        List<ComponentOfFoodDTO> list = repository.findAll().stream()
                .map(ComponentOfFoodDTO::mapping).collect(Collectors.toList());

        return ApiResult.successResponse(list);
    }

    public ApiResult<ComponentOfFoodDTO> edit(ComponentOfFoodDTO componentOfFoodDTO) {
        Optional<ComponentOfFood> optional;
        if ((optional = repository.findById(componentOfFoodDTO.getId())).isEmpty())
            throw RestException.restThrow("this component does not exist", HttpStatus.CONFLICT);
        ComponentOfFood component = repository.save(componentOfFoodDTO.getComponent());

        return ApiResult.successResponse(ComponentOfFoodDTO.mapping(component));
    }

}
