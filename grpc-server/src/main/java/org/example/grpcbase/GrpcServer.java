package org.example.grpcbase;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceGrpcImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
