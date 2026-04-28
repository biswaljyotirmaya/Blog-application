package com.jb.blog.service;

import com.jb.blog.DTO.UserData;
import com.jb.blog.entity.Post;
import com.jb.blog.entity.User;

import java.util.List;

public interface IUserService {

    public String addUser(UserData userData);

    public List<UserData> findAll();

    public UserData findById(Long id);

    public String updateUser(UserData user);

    public String deleteUser(Long id);
}
