package com.jb.blog.service;

import com.jb.blog.DTO.LoginData;
import com.jb.blog.DTO.UserData;

import java.util.List;

public interface IUserService {

    public String addUser(UserData userData);

    public String login(LoginData loginData);

    public List<UserData> findAll();

    public UserData findById(Long id);

    public String updateUser(Long id,UserData user);

    public String deleteUser(Long id);
}
