package ru.mfua.botalov.warehouse.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.mfua.botalov.warehouse.entity.OrderItemEntity;
import ru.mfua.botalov.warehouse.exception.OrderException;
import ru.mfua.botalov.warehouse.exception.UserException;
import ru.mfua.botalov.warehouse.model.OrderDto;
import ru.mfua.botalov.warehouse.repository.OrderRepository;
import ru.mfua.botalov.warehouse.repository.UserRepository;
import ru.mfua.botalov.warehouse.service.mapper.OrderItemMapper;
import ru.mfua.botalov.warehouse.service.mapper.OrderMapper;
import ru.mfua.botalov.warehouse.service.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Utils utils;

    public String addOrder(OrderDto orderDto) {
        var orderEntity = orderMapper.fromDTO(orderDto);

        List<OrderItemEntity> items = orderDto.getItems().stream()
                .map(orderItemMapper::fromDTO)
                .collect(Collectors.toList());

        items.forEach(value -> value.setOrderEntity(orderEntity));
        orderEntity.setItems(items);

        var savedId = orderRepository.save(orderEntity).getId();

        return String.valueOf(savedId);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        var oldOrderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Заказ не найден"));

        var newOrderEntity = orderMapper.fromDTO(orderDto);

        utils.merge(newOrderEntity, oldOrderEntity);

        return orderMapper.toDTO(orderRepository.save(newOrderEntity));
    }

    public OrderDto getOrderById(Long id) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException("Заказ не найден"));

        return orderMapper.toDTO(orderEntity);
    }

    public List<OrderDto> getOrderByParameter(Long userId, String clientId) {

        if (!ObjectUtils.isEmpty(userId) && !ObjectUtils.isEmpty(clientId)) {

            var userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserException("Пользователь не найден"));

            return orderRepository.findAllByUserEntityOrClientId(userEntity, clientId)
                    .stream()
                    .map(orderMapper::toDTO)
                    .collect(Collectors.toList());
        } else if (!ObjectUtils.isEmpty(userId)) {
            var userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserException("Пользователь не найден"));

            return orderRepository.findALlByUserEntity(userEntity)
                    .stream()
                    .map(orderMapper::toDTO)
                    .collect(Collectors.toList());
        } else if (!ObjectUtils.isEmpty(clientId)) {
            return orderRepository.findAllByClientId(clientId)
                    .stream()
                    .map(orderMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
