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
public class SendSmsRequestDto {
    @JsonProperty("client_id")
    String clientId;
    String theme;
    String text;
}
