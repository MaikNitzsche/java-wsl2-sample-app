package de.msg.hellomsg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MyRestController {

    @GetMapping("/{name}")
    public String getUser(@PathVariable String name) {
        return "Hello " + name + "!";
    }

}


