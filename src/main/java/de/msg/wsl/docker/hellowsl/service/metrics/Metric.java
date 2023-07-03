package de.msg.wsl.docker.hellowsl.service.metrics;

public enum Metric {
    PROCESSING_MESSAGE("my_rest_controller_timer", "className", "methodName");

    String name;
    String[] tags;

    Metric(String name, String... tags) {
        this.name = name;
        this.tags = tags;
    }
}
