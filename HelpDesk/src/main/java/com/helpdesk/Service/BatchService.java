package com.helpdesk.Service;


import com.helpdesk.Model.Batch;
import com.helpdesk.Repository.BatchRepo;
import com.helpdesk.dto.BatchDTO;
import com.helpdesk.mapper.BatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepo batchRepo;
    private final BatchMapper batchMapper;

    public BatchDTO findById(Long id) {
       Batch batch = batchRepo.findById(id).orElse(null);
       return batchMapper.toDTO(batch);

    }

    public List<BatchDTO> findAllBatches() {
        return batchRepo.findAll().stream()
                .map(batchMapper::toDTO)
                .collect(Collectors.toList());

    }


    public BatchDTO createBatch(BatchDTO batchDTO) {
        Batch batch = batchMapper.toEntity(batchDTO);
        return batchMapper.toDTO(batchRepo.save(batch));
    }

    public BatchDTO updateBatch(BatchDTO batchDTO) {
        Batch existing = batchRepo.findById(batchDTO.getBatchId())
                .orElseThrow(()->new RuntimeException("Batch not found"));

        existing.setBatchName(batchDTO.getBatchName());
        Batch update = batchRepo.save(existing);
        return batchMapper.toDTO(update);
    }

    public void deleteBatch(Long id) {
        batchRepo.deleteById(id);
    }





}
