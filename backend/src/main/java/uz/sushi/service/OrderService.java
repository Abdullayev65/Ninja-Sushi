package uz.sushi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.sushi.entity.*;
import uz.sushi.entity.enums.OrderStatusEnum;
import uz.sushi.exceptions.RestException;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.dto.OrderDTO;
import uz.sushi.repository.*;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderSetRepository orderSetRepository;
    private final SetRepository setRepository;

    public ApiResult<Integer> addProduct(User user, Integer productId, Integer count) {
        checkUser(user);
        Order order = orderedOrderOfClient(user);
        if (order==null)
            order = new Order(user);
        Integer newCount = order.addProductQuantity(productId, count);
        if (newCount==null && count > 0) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> RestException
                            .restThrow("Product Not Fount", HttpStatus.CONFLICT));
            OrderProduct orderProduct = orderProductRepository.save(OrderProduct.mapping(
                    product,
                    order, count));

            //TODO
            Set<OrderProduct> orderProducts = order.getOrderProducts();
            orderProducts.add(orderProduct);
            newCount = count;
        }

        return ApiResult.successResponse(newCount);
    }

    public ApiResult<Integer> addSet(User user, Integer setId, Integer count) {
        checkUser(user);
        Order order = orderedOrderOfClient(user);
        if (order==null)
            order = new Order(user);
        Integer newCount = order.addSetQuantity(setId, count);
        if (newCount==null && count > 0) {
            SetOfProduct setOfProduct = setRepository.findById(setId)
                    .orElseThrow(()-> RestException
                            .restThrow("Set Not Fount", HttpStatus.CONFLICT));
            OrderSet orderSet = orderSetRepository.save(OrderSet.mapping(
                    setOfProduct,
                    order, count));

            Set<OrderSet> orderSets = order.getOrderSets();
            orderSets.add(orderSet);
            newCount = count;
        }

        return ApiResult.successResponse(newCount);
    }

    public ApiResult<OrderDTO> orderedItems(User user) {
        checkUser(user);
        Order order = orderedOrderOfClient(user);
        return ApiResult
                .successResponse(OrderDTO.mapping(order));
    }





    private Order orderedOrderOfClient(User client) {
        return orderRepository.findOrderByClientIsAndStatusEnumIs(client, OrderStatusEnum.ORDERED);
    }
    private void checkUser(User user) {
        if (user==null||user.getId()==null)
            throw RestException.restThrow("User not found", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
