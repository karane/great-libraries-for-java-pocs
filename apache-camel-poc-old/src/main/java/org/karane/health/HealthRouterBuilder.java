package org.karane.health;

import org.apache.camel.builder.RouteBuilder;

public class HealthRouterBuilder extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:foo?period=1000").routeId("timer")
            .bean(MonkeyHealthCheck.class, "chaos")
            .log("${body}");

    }
}