package org.karane;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("file:input?noop=true")
          .to("file:output");
    }
}
