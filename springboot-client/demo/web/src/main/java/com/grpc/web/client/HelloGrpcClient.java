package com.grpc.web.client;

import hello.HelloServiceGrpc;
import hello.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloGrpcClient {
    @Value("${grpc.server.host}")
    private String helloGrpcServiceHost;

    @Value("${grpc.server.port}")
    private int helloGrpcServicePort;

    private  final  static Logger logger = LoggerFactory.getLogger(HelloGrpcClient.class);



    public String talkie(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(helloGrpcServiceHost, helloGrpcServicePort)
                .usePlaintext()
                .build();
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        Message message = Message.newBuilder().setContent("from springboot client").build();
        var serverMessage = stub.talkie(message);
        logger.info("Server message {}", serverMessage);
        channel.shutdown();
        return serverMessage.getContent();
    }
}
