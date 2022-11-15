package ru.mfua.botalov.warehouse.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    long id;

    OffsetDateTime created;

    @JsonProperty("user_id")
    long userId;

    @JsonProperty("delivery_address")
    String deliveryAddress;

    @JsonProperty("client_fio")
    String clientFio;

    @JsonProperty("client_id")
    String clientId;

    String uid;

    @JsonProperty("delivery_time")
    OffsetDateTime deliveryTime;
    String status;
    String comment;
    Priority priority;
    List<OrderItemDto> items;


    public static enum Priority {
        LOW("LOW"),
        MEDIUM("MEDIUM"),
        HIGHT("HIGHT");

        private String value;

        private Priority(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        @JsonCreator
        public static OrderDto.Priority fromValue(String value) {
            OrderDto.Priority[] var1 = values();
            int var2 = var1.length;

            for (Priority b : var1) {
                if (b.value.equals(value)) {
                    return b;
                }
            }

            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }
}
