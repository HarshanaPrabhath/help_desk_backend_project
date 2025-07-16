package com.helpdesk.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private Long announcementID;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private Long userId;

}
