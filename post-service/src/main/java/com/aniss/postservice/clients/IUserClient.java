package com.aniss.postservice.clients;

import com.aniss.UserEntityResponse;
import com.aniss.postservice.models.User;

import java.util.List;

public interface IUserClient {
    User getUserById(Long id);

    List<User> getAll();
}
