package org.karane;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public final class CamelConsole {

    public static void main(String[] args) throws Exception {
        // create a CamelContext
        try (CamelContext camel = new DefaultCamelContext()) {

            camel.addRoutes(createConsoleRoute());

            camel.start();

            Thread.sleep(Integer.MAX_VALUE);
        }
    }

    static RouteBuilder createConsoleRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("stream:in?promptMessage=Enter text: ")
                        .routeId("console-uppercase-route")
                        .log("Received: ${body}")
                        .transform().simple("${body.toUpperCase()}")
                        .to("stream:out");
            }
        };
    }
}