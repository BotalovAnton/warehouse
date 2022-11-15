package ru.mfua.botalov.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mfua.botalov.warehouse.client.SmsServiceFeignClient;
import ru.mfua.botalov.warehouse.entity.OrderEntity;
import ru.mfua.botalov.warehouse.model.SendSmsRequestDto;
import ru.mfua.botalov.warehouse.repository.OrderRepository;

import java.util.List;
import static ru.mfua.botalov.warehouse.model.Constants.WAITING_DELIVERY;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final OrderRepository orderRepository;
    private final SmsServiceFeignClient smsServiceFeignClient;

    private static final String SMS_DELIVERY_TEXT = "Ваш заказ № %s передан в службу доставки. " +
            "Ожидайте, скоро с вами свяжутся";
    private static final String DELIVERY = "DELIVERY";



    @Scheduled(cron = "${schedulers.send-sms-delivery.cron}", zone = "Europe/Moscow")
    public void startSendSmsDeliveryJob() {
        List<OrderEntity> orders = orderRepository.findByStatus(WAITING_DELIVERY);

        orders.forEach(order ->
            smsServiceFeignClient.sendSms(SendSmsRequestDto.builder()
                    .clientId(order.getClientId())
                    .theme(DELIVERY)
                    .text(String.format(SMS_DELIVERY_TEXT, order.getUid()))
                    .build()));

    }
}
