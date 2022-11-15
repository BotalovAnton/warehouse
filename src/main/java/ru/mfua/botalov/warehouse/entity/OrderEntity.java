package ru.mfua.botalov.warehouse.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    OffsetDateTime created;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity userEntity;
    String deliveryAddress;
    String clientFio;
    String clientId;
    String uid;
    OffsetDateTime deliveryTime;
    String status;
    String comment;
    String priority;
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    List<OrderItemEntity> items;
}
