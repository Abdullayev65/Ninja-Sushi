package uz.sushi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.sushi.entity.Product;
import uz.sushi.entity.User;
import uz.sushi.entity.enums.ProductType;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.ProductCollectionDTO;
import uz.sushi.payload.add.AddProduct;
import uz.sushi.payload.dto.ProductDTO;
import uz.sushi.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ApiResult<ProductCollectionDTO> topProducts() {
        User user = null;
        List<Product> rolls = productRepository.findAllByTypeIs(ProductType.ROLL,
                PageRequest.of(0, 13, Sort.by("id")));

        return null;
    }

    public ApiResult<ProductDTO> addProduct(AddProduct addProduct) {

        return null;
    }

}
