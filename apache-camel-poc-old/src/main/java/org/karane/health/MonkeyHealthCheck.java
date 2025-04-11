package org.karane.health;

import java.util.Map;

import org.apache.camel.health.HealthCheckResultBuilder;
import org.apache.camel.impl.health.AbstractHealthCheck;
import org.apache.camel.spi.annotations.HealthCheck;

@HealthCheck("monkey-check")
public class MonkeyHealthCheck extends AbstractHealthCheck {

    private static boolean up = true;

    public MonkeyHealthCheck() {
        super("custom", "monkey");
    }

    @Override
    protected void doCall(HealthCheckResultBuilder builder, Map<String, Object> options) {
        builder.detail("monkey", "The chaos monkey was here");
         if (up) {
             builder.up();
         } else {
             builder.down();
         }
    }

    public static String chaos() {
        up = !up;
        return up ? "All is okay" : "Chaos monkey was here";
    }

}