package org.example.grpcbase.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverRegistry;
import io.grpc.internal.DnsNameResolverProvider;
import org.example.grpc.base.HelloRequest;
import org.example.grpc.base.HelloResponse;
import org.example.grpc.base.HelloServiceGrpc;

public class GrpcClient {

    public static void main(String[] args) {
        // Register DnsNameResolverProvider
        System.out.println("Registering DnsNameResolverProvider");
        NameResolverRegistry.getDefaultRegistry().register(new DnsNameResolverProvider());

        // Defining default scheme
        System.out.println(NameResolverRegistry.getDefaultRegistry().getDefaultScheme());

        // Opening channel with dns scheme explicitly specified
        ManagedChannel channel = ManagedChannelBuilder.forTarget("dns:///localhost:8081")
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
