package com.helpdesk.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "questionId"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private boolean anonymous;
    private double vote;
    private String userName;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    @PrePersist
    public void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    public boolean getAnonymous() {
        return anonymous;
    }


}
