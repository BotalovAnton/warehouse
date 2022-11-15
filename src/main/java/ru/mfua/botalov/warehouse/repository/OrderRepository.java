package ru.mfua.botalov.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mfua.botalov.warehouse.entity.OrderEntity;
import ru.mfua.botalov.warehouse.entity.UserEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByUserEntityOrClientId(UserEntity userEntity, String clientId);

    List<OrderEntity> findALlByUserEntity(UserEntity userEntity);

    List<OrderEntity> findAllByClientId(String clientId);

    List<OrderEntity> findByStatus(String status);
}
