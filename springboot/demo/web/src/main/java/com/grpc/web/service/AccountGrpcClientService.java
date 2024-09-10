package com.grpc.web.service;

import com.grpc.account.stubs.AccountServiceGrpc;
import com.grpc.account.stubs.RegisterRequest;
import com.grpc.account.stubs.RegisterResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class AccountGrpcClientService {
    @GrpcClient("accountService")
    private AccountServiceGrpc.AccountServiceBlockingStub accountServiceBlockingStub;
    public RegisterResponse register(RegisterRequest request) {
        return accountServiceBlockingStub.register(request);
    }
}
