package ru.mfua.botalov.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.OffsetDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String login;
    String password;
    String name;
    String surname;
    String patronymic;
    String phone;
    String email;
    String position;
    OffsetDateTime created;
    @JsonProperty("account_is_locked")
    boolean accountIsLocked;
    @JsonProperty("password_expiration_date")
    OffsetDateTime passwordExpirationDate;
}
