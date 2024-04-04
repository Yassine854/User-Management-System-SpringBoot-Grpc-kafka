package com.aniss.userservice;

import com.aniss.*;
import com.aniss.userservice.entities.User;
import com.aniss.userservice.services.UserService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
public class UserServiceGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserService userService;

    @Override
    public void getUserById(UserEntityRequest request, StreamObserver<UserEntityResponse> responseObserver) {
        Optional<User> user = userService.get((long)request.getId());
        if ( user.isPresent() )
        {
            System.out.println("found it !");
            responseObserver.onNext(UserEntityResponse.newBuilder()
                    .setName(user.get().getName())
                    .setId(user.get().getId())
                    .build());
            responseObserver.onCompleted();
        }
        else
        {
            responseObserver.onError(Status.NOT_FOUND.asException());
        }
    }


    @Override
    public void getAllUsers(GetAllUsersRequest request, StreamObserver<GetAllUsersResponse> responseObserver) {
        List<User> users = userService.GetAllUsers();
        List<UserEntityResponse> userEntityResponses = users.stream()
                .map(user -> UserEntityResponse.newBuilder()
                        .setId(user.getId())
                        .setName(user.getName())
                        .build())
                .collect(Collectors.toList());

        responseObserver.onNext(GetAllUsersResponse.newBuilder()
                .addAllUsers(userEntityResponses)
                .build());
        responseObserver.onCompleted();
    }
}
