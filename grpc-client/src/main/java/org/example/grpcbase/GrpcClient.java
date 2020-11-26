package org.example.grpcbase;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.base.HelloRequest;
import org.example.grpc.base.HelloResponse;
import org.example.grpc.base.HelloServiceGrpc;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Mark")
                .setLastName("Chesnavsky")
                .build());

        System.out.println("Result: " + helloResponse.getGreeting());

        channel.shutdown();
    }
}
