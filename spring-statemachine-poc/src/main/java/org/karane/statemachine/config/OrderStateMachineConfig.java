package org.karane.statemachine.config;

import org.karane.statemachine.model.OrderEvent;
import org.karane.statemachine.model.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    private static final Logger log = LoggerFactory.getLogger(OrderStateMachineConfig.class);

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(new StateMachineListenerAdapter<>() {
                    @Override
                    public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                        String fromState = from != null ? from.getId().name() : "NONE";
                        log.info("State changed: {} -> {}", fromState, to.getId());
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.NEW)
                .states(EnumSet.allOf(OrderState.class))
                .end(OrderState.DELIVERED)
                .end(OrderState.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                // Happy path: NEW -> PAID -> PROCESSING -> SHIPPED -> DELIVERED
                .withExternal()
                    .source(OrderState.NEW).target(OrderState.PAID)
                    .event(OrderEvent.PAY)
                .and()
                .withExternal()
                    .source(OrderState.PAID).target(OrderState.PROCESSING)
                    .event(OrderEvent.PROCESS)
                .and()
                .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.SHIPPED)
                    .event(OrderEvent.SHIP)
                .and()
                .withExternal()
                    .source(OrderState.SHIPPED).target(OrderState.DELIVERED)
                    .event(OrderEvent.DELIVER)
                .and()
                // Cancel path: NEW or PROCESSING -> CANCELLED
                .withExternal()
                    .source(OrderState.NEW).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL)
                .and()
                .withExternal()
                    .source(OrderState.PROCESSING).target(OrderState.CANCELLED)
                    .event(OrderEvent.CANCEL);
    }
}
