package ru.mfua.botalov.warehouse.service;

import ru.mfua.botalov.warehouse.model.OrderDto;
import java.util.List;

public interface OrderService {

    String addOrder(OrderDto orderDto);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    OrderDto getOrderById(Long id);

    List<OrderDto> getOrderByParameter(Long userId, String clientId);

    void deleteOrder(Long id);
}
