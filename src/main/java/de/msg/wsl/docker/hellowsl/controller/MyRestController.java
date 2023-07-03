package de.msg.wsl.docker.hellowsl.controller;

import de.msg.wsl.docker.hellowsl.service.metrics.MetricProcessedTimeInterceptable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class MyRestController {

    @GetMapping("/{name}")
    @MetricProcessedTimeInterceptable
    public String getUser(@PathVariable String name) {
        log.info("Function getUser was called with following parameter: {}", name);
        return "Hello " + name + "!";
    }

}


