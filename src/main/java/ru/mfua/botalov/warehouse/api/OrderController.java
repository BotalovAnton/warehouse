package ru.mfua.botalov.warehouse.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mfua.botalov.warehouse.model.OrderDto;
import ru.mfua.botalov.warehouse.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestParam(name = "id") Long id,
                                                @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }

    @GetMapping
    public ResponseEntity<OrderDto> getOrderById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByParameter(@RequestParam(name = "user_id", required = false) Long userId,
                                                               @RequestParam(name = "client_id", required = false) String clientId) {

        return ResponseEntity.ok(orderService.getOrderByParameter(userId, clientId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@RequestParam(name = "id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
