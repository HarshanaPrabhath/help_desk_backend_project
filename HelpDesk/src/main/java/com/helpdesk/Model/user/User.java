package com.helpdesk.Model.user;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.helpdesk.Model.announcemnt.Announcement;
import com.helpdesk.Model.answer.Answer;
import com.helpdesk.Model.batch.Batch;
import com.helpdesk.Model.department.Department;
import com.helpdesk.Model.question.Question;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId"
)

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
       setCreatedAt(LocalDateTime.now());
    }


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;


    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Announcement>  announcements;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;


}
