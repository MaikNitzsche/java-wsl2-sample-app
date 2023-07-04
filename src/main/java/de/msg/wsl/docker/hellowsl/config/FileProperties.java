package de.msg.wsl.docker.hellowsl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "wsl.docker")
@Component
@Getter
@Setter
public class FileProperties {

    private String filePath;
    private String fileName;

}