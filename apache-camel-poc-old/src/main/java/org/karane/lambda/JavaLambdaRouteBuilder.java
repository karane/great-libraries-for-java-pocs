package org.karane.lambda;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

import java.util.Objects;
import java.util.Date;

import org.slf4j.Logger;

public class JavaLambdaRouteBuilder extends RouteBuilder {

    final Logger logger;

    public JavaLambdaRouteBuilder(Logger logger) {
        this.logger = logger;
    }

    @Override
        public void configure() throws Exception {
            from("timer:simple?includeMetadata=true&period=3000")
                .id("simple-route")
                .transform()
                    .exchange(this::dateToTime)
                .process()
                    .message(this::log)
                .process()
                    .body(this::log)
                .choice()
                    .when()
                        .body(Integer.class, b -> (b & 1) == 0)
                        .log("Received even number")
                    .when()
                        .body(Integer.class, b -> (b & 1) != 0)
                        .log("Received odd number")
                    .when()
                        .body(Objects::isNull)
                        .log("Received null body")
                .endChoice();
        }

        private Long dateToTime(Exchange e) {
            return e.getProperty(Exchange.TIMER_FIRED_TIME, Date.class).getTime();
        }

        private void log(Object b) {
            logger.info("body is: {}", b);
        }

        private void log(Message m) {
            logger.info("message is: {}", m);
        }
    }