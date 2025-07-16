package com.helpdesk.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "answerId"
)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private String description;
    private LocalDateTime createdDate;
    private long vote;

    @PrePersist
    public void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
