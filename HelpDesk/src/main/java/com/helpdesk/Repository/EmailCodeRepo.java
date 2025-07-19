package com.helpdesk.Repository;

import com.helpdesk.Model.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailCodeRepo extends JpaRepository<EmailCode,Integer> {

    Optional<EmailCode> findByEmail(String email);

    @Query("SELECT e FROM EmailCode e WHERE e.email = :email AND e.status = true")
    Optional<EmailCode> findByEmailAndStatus(String email);

}
