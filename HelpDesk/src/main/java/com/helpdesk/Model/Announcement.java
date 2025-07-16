package com.helpdesk.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "announcementId"
)

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcementId;
    private String title;

    private String description;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDateTime.now());
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
