package ru.mfua.botalov.warehouse.service.mapper;

import org.mapstruct.Mapper;
import ru.mfua.botalov.warehouse.entity.ProductEntity;
import ru.mfua.botalov.warehouse.model.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDTO(ProductEntity productEntity);

    ProductEntity fromDTO(ProductDto productDto);
}
