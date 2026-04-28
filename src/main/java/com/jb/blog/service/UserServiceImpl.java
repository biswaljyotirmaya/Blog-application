package com.jb.blog.service;

import com.jb.blog.DTO.LoginData;
import com.jb.blog.DTO.UserData;
import com.jb.blog.entity.User;
import com.jb.blog.repo.IUsersRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private HttpSession session;

    @Autowired
    private IUsersRepo userRepo;

    @Override
    public String addUser(UserData userData) {
        if (userRepo.findByEmail(userData.getEmail()) != null) {
            return "ERROR: Email already in use";
        }
        User user = new User();
        BeanUtils.copyProperties(userData, user);
        userRepo.save(user);
        return "SUCCESS: User registered successfully";
    }

    @Override
    public String login(LoginData loginData) {
        if (loginData == null) {
            return "ERROR: Credential can't be empty";
        } else if (loginData.getEmail().isEmpty()) {
            return "ERROR: Email can't be empty";
        } else if (loginData.getPassword().isEmpty()) {
            return "ERROR: Password can't be empty";
        }
        User userData = userRepo.findByEmail(loginData.getEmail());
        if (userData == null) {
            return "ERROR: User not found, please try with valid email";
        } else if (!userData.getPassword().equals(loginData.getPassword())) {
            return "ERROR: Passwords don't match";
        }
        userData.setPassword("");
        session.setAttribute("loggedInUser", userData);
        return "SUCCESS: User logged in successfully";
    }

    @Override
    public List<UserData> findAll() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) return new ArrayList<>();
        List<UserData> list = new ArrayList<>();
        for (User user : users) {
            UserData userData = new UserData();
            BeanUtils.copyProperties(user, userData);
            list.add(userData);
        }
        return list;
    }

    @Override
    public UserData findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) return null;
        UserData userData = new UserData();
        BeanUtils.copyProperties(user.get(), userData);
        return userData;
    }

    @Override
    public String updateUser(Long id,UserData userdata) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) return "ERROR: User not found";
        user.get().setFirstName(userdata.getFirstName());
        user.get().setLastName(userdata.getLastName());
        userRepo.save(user.get());
        return "SUCCESS: User updated successfully";
    }

    @Override
    public String deleteUser(Long id) {
        return "";
    }
}
