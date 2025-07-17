package com.helpdesk.Repository;

import com.helpdesk.Model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {

}
