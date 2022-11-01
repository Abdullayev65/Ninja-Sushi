package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.ProductCollectionDTO;
import uz.sushi.service.ProductService;

@RestController
@RequestMapping(path = ProductController.PRODUCT_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
public class ProductController {
    public static final String PRODUCT_CONTROLLER_BASE_PATH = "/api/product";
    public static final String TOP_POSITIONS_SUSHI = "/top-positions-products";
    public static final String SIGN_UP_PATH = "/sign-up";
    private final ProductService productService;

    @PostMapping(value = TOP_POSITIONS_SUSHI)
    ApiResult<ProductCollectionDTO> topProducts() {
        return productService.topProducts();
    }



}
