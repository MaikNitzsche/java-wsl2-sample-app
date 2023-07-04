package de.msg.wsl.docker.hellowsl.controller;

import de.msg.wsl.docker.hellowsl.config.FileProperties;
import de.msg.wsl.docker.hellowsl.service.metrics.MetricProcessedTimeInterceptable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/hello")
@Slf4j
@AllArgsConstructor
public class MyRestController {

    FileProperties fileProperties;

    @GetMapping("/{name}")
    @MetricProcessedTimeInterceptable
    public String getUser(@PathVariable String name) throws IOException {
        log.info("Function getUser was called with following parameter: {}", name);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        FileWriter fw = new FileWriter(fileProperties.getFilePath() + "/" + fileProperties.getFileName(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(localDate + " " + localTime + "\t" + name);
        bw.newLine();
        bw.close();

        return "Hello " + name + "!";
    }

}


