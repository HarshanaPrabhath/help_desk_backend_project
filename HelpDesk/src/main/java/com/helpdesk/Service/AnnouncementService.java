package com.helpdesk.Service;

import com.helpdesk.Model.announcemnt.Announcement;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.AnnouncementRepo;
import com.helpdesk.Repository.UserRepo;
import com.helpdesk.dto.AnnouncementDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepo announcementRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    public AnnouncementDTO createAnnouncements(AnnouncementDTO announcementDTO) {
        Announcement announcement = modelMapper.map(announcementDTO, Announcement.class);

        if(announcementDTO.getUserId() != null) {
            User user = userRepo.findById(announcementDTO.getUserId())
                    .orElseThrow(()->new RuntimeException("User not found"));
            announcement.setUser(user);

        }

       Announcement save = announcementRepo.save(announcement);
        return convertToDTO(save);

    }

    public List<AnnouncementDTO> findAllAnnouncements() {
        List<Announcement> announcements = announcementRepo.findAll();
        return announcements.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AnnouncementDTO> findByTitle(String title) {
        List<Announcement> announcements = announcementRepo.findByTitleContainingIgnoreCase(title);
        return announcements.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    public AnnouncementDTO findById(Long id) {
        Announcement announcement = announcementRepo.findById(id).orElse(null);
        return convertToDTO(announcement);
    }

    public AnnouncementDTO update(AnnouncementDTO announcementDTO) {
        Announcement announcement = announcementRepo.findById(announcementDTO.getAnnouncementID())
                .orElseThrow(()->new RuntimeException("Announcement not found"));
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setDescription(announcementDTO.getDescription());
        announcementRepo.save(announcement);
        return convertToDTO(announcement);

    }
    public void delete(Long id) {
       announcementRepo.deleteById(id);

    }

    private AnnouncementDTO convertToDTO(Announcement announcement) {
        AnnouncementDTO DTO = modelMapper.map(announcement, AnnouncementDTO.class);

        DTO.setUserId(announcement.getUser()!=null
        ? announcement.getUser().getUserId():null);

        return DTO;


    }
}
