package ru.mfua.botalov.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDto {
    long id;
    @JsonProperty("product_id")
    long productId;
    @JsonProperty("product_name")
    String productName;
    int count;
}
