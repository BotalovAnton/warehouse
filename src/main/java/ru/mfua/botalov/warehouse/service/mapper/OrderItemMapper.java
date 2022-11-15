package ru.mfua.botalov.warehouse.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mfua.botalov.warehouse.entity.OrderItemEntity;
import ru.mfua.botalov.warehouse.model.OrderItemDto;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "productEntity.id")
    OrderItemDto toDTO(OrderItemEntity orderItemEntity);

    @InheritInverseConfiguration
    OrderItemEntity fromDTO(OrderItemDto orderItemDto);
}
