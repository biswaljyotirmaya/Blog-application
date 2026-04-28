package com.jb.blog.service;

import com.jb.blog.DTO.UserData;
import com.jb.blog.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public String addUser(UserData userData) {
        return "";
    }

    @Override
    public List<UserData> findAll() {
        return List.of();
    }

    @Override
    public UserData findById(Long id) {
        return null;
    }

    @Override
    public String updateUser(UserData user) {
        return "";
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }
}
