package com.user_interface.user_interface.repository;

import com.user_interface.user_interface.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User,Long> {
    User getByUsername(String name);
}
