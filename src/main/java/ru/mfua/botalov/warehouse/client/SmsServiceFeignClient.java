package ru.mfua.botalov.warehouse.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mfua.botalov.warehouse.model.SendSmsRequestDto;

@FeignClient(name = "sms-service", url = "client.sms-service.url")
public interface SmsServiceFeignClient {

    @PostMapping("/send")
    ResponseEntity<Void> sendSms(@RequestBody SendSmsRequestDto requestDto);
}
