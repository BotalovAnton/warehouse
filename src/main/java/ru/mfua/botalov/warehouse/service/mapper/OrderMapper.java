package ru.mfua.botalov.warehouse.service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mfua.botalov.warehouse.entity.OrderEntity;
import ru.mfua.botalov.warehouse.model.OrderDto;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "userId", source = "userEntity.id")
    OrderDto toDTO(OrderEntity orderEntity);

    @InheritInverseConfiguration
    OrderEntity fromDTO(OrderDto orderDto);

    @AfterMapping
    default void setOrder(@MappingTarget OrderEntity orderEntity) {
        orderEntity.getItems().forEach(value -> value.setOrderEntity(orderEntity));
    }
}
