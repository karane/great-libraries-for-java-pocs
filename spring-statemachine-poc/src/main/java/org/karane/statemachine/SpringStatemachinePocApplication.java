package org.karane.statemachine;

import org.karane.statemachine.model.OrderEvent;
import org.karane.statemachine.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringStatemachinePocApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringStatemachinePocApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringStatemachinePocApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(OrderService orderService) {
        return args -> {
            log.info("=== Spring Statemachine POC - Order Processing Demo ===");

            // Happy path: NEW -> PAID -> PROCESSING -> SHIPPED -> DELIVERED
            log.info("\n--- Scenario 1: Happy Path (Full Delivery) ---");
            String order1 = orderService.createOrder();
            orderService.sendEvent(order1, OrderEvent.PAY);
            orderService.sendEvent(order1, OrderEvent.PROCESS);
            orderService.sendEvent(order1, OrderEvent.SHIP);
            orderService.sendEvent(order1, OrderEvent.DELIVER);
            log.info("Order {} final state: {}", order1, orderService.getState(order1));

            // Cancel from NEW
            log.info("\n--- Scenario 2: Cancel from NEW ---");
            String order2 = orderService.createOrder();
            orderService.sendEvent(order2, OrderEvent.CANCEL);
            log.info("Order {} final state: {}", order2, orderService.getState(order2));

            // Cancel from PROCESSING
            log.info("\n--- Scenario 3: Cancel from PROCESSING ---");
            String order3 = orderService.createOrder();
            orderService.sendEvent(order3, OrderEvent.PAY);
            orderService.sendEvent(order3, OrderEvent.PROCESS);
            orderService.sendEvent(order3, OrderEvent.CANCEL);
            log.info("Order {} final state: {}", order3, orderService.getState(order3));

            // Invalid transition: try to SHIP a NEW order
            log.info("\n--- Scenario 4: Invalid Transition (SHIP from NEW) ---");
            String order4 = orderService.createOrder();
            orderService.sendEvent(order4, OrderEvent.SHIP);
            log.info("Order {} final state: {}", order4, orderService.getState(order4));

            log.info("\n=== Demo Complete ===");
        };
    }
}
