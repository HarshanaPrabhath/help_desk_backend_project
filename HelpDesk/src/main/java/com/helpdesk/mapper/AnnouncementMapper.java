package com.helpdesk.mapper;

import com.helpdesk.Model.announcemnt.Announcement;
import com.helpdesk.dto.AnnouncementDTO;

public class AnnouncementMapper {
    public static AnnouncementDTO toDTO(Announcement announcement) {
        if(announcement == null) {
            return null;
        }

        return AnnouncementDTO.builder()
                .announcementID(announcement.getAnnouncementId())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .createdDate(announcement.getCreatedAt())
                .userId(announcement.getUser()!=null
                        ? announcement.getUser().getUserId()
                        : null)
                .build();

    }

    public static Announcement toEntity(AnnouncementDTO announcementDTO) {
        if(announcementDTO == null) {
            return null;
        }

        return Announcement.builder()
                .announcementId(announcementDTO.getAnnouncementID())
                .title(announcementDTO.getTitle())
                .description(announcementDTO.getDescription())
                .createdAt(announcementDTO.getCreatedDate())
                .build();
    }
}
