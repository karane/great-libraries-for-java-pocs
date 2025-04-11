package org.karane;

import org.apache.camel.main.Main;
import org.karane.health.HealthRouterBuilder;
import org.karane.lambda.JavaLambdaRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MyApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

    private MyApplication() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(new JavaLambdaRouteBuilder(LOGGER));
        main.configure().addRoutesBuilder(new HealthRouterBuilder());
        main.run(args);
    }


}