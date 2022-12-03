package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sushi.entity.User;
import uz.sushi.oap.CurrentUser;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.dto.OrderDTO;
import uz.sushi.service.OrderService;

@RestController
@RequestMapping(path = OrderController.BASE_PATH)
@RequiredArgsConstructor
public class OrderController {
    public static final String BASE_PATH = "/api/order";
    public static final String ADD_PRODUCT = "/product/{id}/{count}";
    public static final String ADD_SET = "/set/{id}/{count}";
    public static final String ORDERED_ITEMS = "/ordered-items";
    private final OrderService orderService;

    @PostMapping(value = ADD_PRODUCT)
    ApiResult<Integer> addProduct(@CurrentUser User user, @PathVariable Integer id, @PathVariable Integer count) {
        return orderService.addProduct(user, id, count);
    }

    @PostMapping(value = ADD_SET)
    ApiResult<Integer> addSet(@CurrentUser User user, @PathVariable Integer id,  @PathVariable Integer count) {
        return orderService.addSet(user, id, count);
    }

    @PostMapping(value = ORDERED_ITEMS)
    ApiResult<OrderDTO> orderedItems(@CurrentUser User user) {
        return orderService.orderedItems(user);
    }

}
