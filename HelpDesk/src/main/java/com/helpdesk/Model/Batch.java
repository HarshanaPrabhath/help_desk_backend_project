package com.helpdesk.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "batchId"
)

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;
    private String batchName;


    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
    private List<User> users;

}
