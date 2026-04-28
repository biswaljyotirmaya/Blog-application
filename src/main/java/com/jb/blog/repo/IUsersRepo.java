package com.jb.blog.repo;

import com.jb.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepo extends JpaRepository<User, Long> {
}
