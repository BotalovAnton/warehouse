package ru.mfua.botalov.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String article;
    String name;
    String category;
    Long count;
    @JsonProperty("storage_place")
    String storagePlace;
}
