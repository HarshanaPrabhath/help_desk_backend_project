package com.helpdesk.mapper;

import com.helpdesk.Model.batch.Batch;
import com.helpdesk.Model.user.User;
import com.helpdesk.dto.BatchDTO;

import java.util.stream.Collectors;

public class BatchMapper {
    public static BatchDTO toDTO(Batch batch) {
        if (batch == null) {
            return null;
        }
        return BatchDTO.builder()
                .batchId(batch.getBatchId())
                .batchName(batch.getBatchName())
                .userIDs(batch.getUsers()!=null
                        ? batch.getUsers().stream().map(User::getUserId).collect(Collectors.toList())
                        :null)
                .build();
    }

    public static Batch toEntity(BatchDTO dto) {
        if(dto == null) {
            return null;
        }

        return Batch.builder()
                .batchId(dto.getBatchId())
                .batchName(dto.getBatchName())
                .build();
    }
}
