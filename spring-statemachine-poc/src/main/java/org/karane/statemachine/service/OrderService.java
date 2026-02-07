package org.karane.statemachine.service;

import org.karane.statemachine.model.OrderEvent;
import org.karane.statemachine.model.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    private final Map<String, StateMachine<OrderState, OrderEvent>> stateMachines = new ConcurrentHashMap<>();

    public OrderService(StateMachineFactory<OrderState, OrderEvent> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    public String createOrder() {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        StateMachine<OrderState, OrderEvent> sm = stateMachineFactory.getStateMachine(orderId);
        sm.startReactively().block();
        stateMachines.put(orderId, sm);
        log.info("Order {} created. State: {}", orderId, sm.getState().getId());
        return orderId;
    }

    public boolean sendEvent(String orderId, OrderEvent event) {
        StateMachine<OrderState, OrderEvent> sm = stateMachines.get(orderId);
        if (sm == null) {
            log.warn("Order {} not found", orderId);
            return false;
        }

        OrderState before = sm.getState().getId();
        var result = sm.sendEvent(Mono.just(MessageBuilder.withPayload(event).build()))
                .blockLast();

        OrderState after = sm.getState().getId();
        boolean accepted = !before.equals(after) || (result != null && result.getResultType().name().equals("ACCEPTED"));

        if (accepted) {
            log.info("Order {}: event {} accepted. State: {} -> {}", orderId, event, before, after);
        } else {
            log.warn("Order {}: event {} DENIED. State remains: {}", orderId, event, before);
        }
        return accepted;
    }

    public OrderState getState(String orderId) {
        StateMachine<OrderState, OrderEvent> sm = stateMachines.get(orderId);
        return sm != null ? sm.getState().getId() : null;
    }
}
