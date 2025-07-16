package com.helpdesk.Repository;

import com.helpdesk.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User>findByFirstNameContainingIgnoreCase(String firstName);
}
