package com.flight.logbook_server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    // GET заявка
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Postman!";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        return "Received: " + message;
    }
}
