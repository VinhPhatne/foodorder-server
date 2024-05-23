package com.foodorder.service;

import com.foodorder.config.JwtProvider;
import com.foodorder.model.User;
import com.foodorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new Exception("User not found ");
        }
        return user;
    }

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromToken(jwt);
        User user=userRepository.findByEmail(email);
        if(user==null) {
            throw new Exception("User not found wiht email "+email);
        }
        return user;
    }
}
