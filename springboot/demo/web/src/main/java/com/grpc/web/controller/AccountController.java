package com.grpc.web.controller;

import com.google.protobuf.DescriptorProtos;
import com.grpc.account.stubs.RegisterRequest;
import com.grpc.account.stubs.RegisterResponse;
import com.grpc.web.dto.RegisterResponseDTO;
import com.grpc.web.service.AccountGrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountGrpcClientService accountGrpcClientService;

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountGrpcClientService accountGrpcClientService) {
        this.accountGrpcClientService = accountGrpcClientService;
    }


    @GetMapping("/test")
    public String test(){
        logger.info("/test");
        return "test";
    }

    @GetMapping("/register")
    public RegisterResponseDTO registerResponse() {
        // seed data
        RegisterRequest registerRequest = RegisterRequest.newBuilder()
                .setUsername("Asd")
                .setPassword("P@ssword123")
                .setFullname("asd")
                .build();

        RegisterResponse registerResponse = accountGrpcClientService.register(registerRequest);
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setFullname(registerResponse.getFullname());
        registerResponseDTO.setUsername(registerResponse.getUsername());
        return registerResponseDTO;
    }
}
