package com.helpdesk.Service;


import com.helpdesk.Model.batch.Batch;
import com.helpdesk.Model.user.User;
import com.helpdesk.Repository.BatchRepo;
import com.helpdesk.dto.BatchDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService {
    @Autowired
    private BatchRepo batchRepo;
    @Autowired
    private ModelMapper modelMapper;

    public BatchDTO findById(Long id) {
       Batch batch = batchRepo.findById(id).orElse(null);
       return convertToDTO(batch);

    }

    public List<BatchDTO> findAllBatches() {
        return batchRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }


    public BatchDTO createBatch(BatchDTO batchDTO) {
        Batch batch = modelMapper.map(batchDTO, Batch.class);
        return convertToDTO(batchRepo.save(batch));
    }

    public BatchDTO updateBatch(BatchDTO batchDTO) {
        Batch existing = batchRepo.findById(batchDTO.getBatchId())
                .orElseThrow(()->new RuntimeException("Batch not found"));

        existing.setBatchName(batchDTO.getBatchName());
        Batch update = batchRepo.save(existing);
        return convertToDTO(update);
    }

    public void deleteBatch(Long id) {
        batchRepo.deleteById(id);
    }



    private BatchDTO convertToDTO(Batch batch) {
        BatchDTO batchDTO = modelMapper.map(batch, BatchDTO.class);

        batchDTO.setUserIDs(batch.getUsers() != null
                ? batch.getUsers().stream().map(User::getUserId).collect(Collectors.toList())
                : null);

        return batchDTO;
    }

}
