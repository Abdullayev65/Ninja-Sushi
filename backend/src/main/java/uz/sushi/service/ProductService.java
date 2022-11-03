package uz.sushi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.sushi.entity.Product;
import uz.sushi.entity.SetOfProduct;
import uz.sushi.entity.User;
import uz.sushi.entity.enums.ProductType;
import uz.sushi.exceptions.RestException;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.ProductCollectionDTO;
import uz.sushi.payload.add.AddProduct;
import uz.sushi.payload.dto.ProductDTO;
import uz.sushi.payload.dto.SetDTO;
import uz.sushi.repository.ProductRepository;
import uz.sushi.repository.SetRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SetRepository setRepository;

    public ApiResult<ProductCollectionDTO> topProducts(User user) {
        Set<Product> favorites;
        ProductCollectionDTO collectionDTO = new ProductCollectionDTO();
        if (user == null ||
                (favorites = user.getFavorites()) == null)
            favorites = Set.of();
        collectionDTO.setSushi(productList(ProductType.SUSHI, 0, 13, favorites));
        collectionDTO.setRolls(productList(ProductType.ROLL, 0, 13, favorites));
        collectionDTO.setSnacks(productList(ProductType.SNACKS, 0, 13, favorites));
        collectionDTO.setDrinks(productList(ProductType.DRINK, 0, 13, favorites));

        collectionDTO.setSets(setList(0, 13,
                (user == null || user.getFavoriteSets() == null) ?
                        Set.of() : user.getFavoriteSets()));

        return ApiResult.successResponse(collectionDTO);
    }

    public ApiResult<ProductDTO> addProduct(AddProduct addProduct) {
        if (!productRepository.findByName(addProduct.getName())) {
            throw RestException.restThrow("This product already exists!", HttpStatus.CONFLICT);
        }
        Product product = new Product();
        product.setName(addProduct.getName());
        product.setType(addProduct.getType());
        product.setWeight(addProduct.getWeight());
        product.setPrice(addProduct.getPrice());
        product.setImagUrl(addProduct.getImagUrl());
        product.setComponents(addProduct.getComponents());

        Product save = productRepository.save(product);

        return ApiResult.successResponse("Product added!", ProductDTO.mapping(save, false));
    }


    private List<ProductDTO> productList(ProductType productType, int page, int size, Set<Product> favorites) {
        return productRepository.findAllByTypeIs(productType,
                        PageRequest.of(page, size, Sort.by("id")))
                .stream()
                .map((p) -> ProductDTO.mapping(p, favorites.contains(p)))
                .collect(Collectors.toList());
    }

    private List<SetDTO> setList(int page, int size, Set<SetOfProduct> favorites) {
        return setRepository.findAll(
                        PageRequest.of(page, size, Sort.by("id")))
                .stream()
                .map((s) -> SetDTO.mapping(s, favorites.contains(s)))
                .collect(Collectors.toList());
    }

}
