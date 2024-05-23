package com.foodorder.service;

import com.foodorder.model.User;

public interface UserService {
    public User findUserByEmail(String email) throws Exception;

    public User findUserByJwtToken(String jwt) throws Exception;
}
