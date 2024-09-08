package com.grpc.web.controller;

import com.grpc.web.client.HelloGrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private  final HelloGrpcClient helloGrpcClient;

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    public HelloController(HelloGrpcClient helloGrpcClient) {
        this.helloGrpcClient = helloGrpcClient;
    }

    @GetMapping("/test")
    public String test(){
        logger.info("/test");
        return "test";
    }

    @GetMapping("/talkie")
    public String talkie() {
        logger.info("Talkie controller");
        String response = helloGrpcClient.talkie();
        logger.info(response);
        return helloGrpcClient.talkie();
    }
}
