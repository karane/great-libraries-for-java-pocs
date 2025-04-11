package org.karane.kafka;

import org.apache.camel.builder.RouteBuilder;

public class KafkaConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:{{kafka.topic}}?brokers={{kafka.bootstrap-servers}}")
            .log("Received message from Kafka: ${body}");
    }
}
