package com.fourbitlabs.employee_management_system.service;

import com.fourbitlabs.employee_management_system.dto.request.BatchRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.UserRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchResponseDto;
import com.fourbitlabs.employee_management_system.entity.AnalystProfile;
import com.fourbitlabs.employee_management_system.entity.Batch;
import com.fourbitlabs.employee_management_system.entity.TrainerProfile;
import com.fourbitlabs.employee_management_system.entity.User;
import com.fourbitlabs.employee_management_system.enums.BatchStatus;
import com.fourbitlabs.employee_management_system.exception.ResourceNotFoundException;
import com.fourbitlabs.employee_management_system.repository.AnalystProfileRepository;
import com.fourbitlabs.employee_management_system.repository.BatchRepository;
import com.fourbitlabs.employee_management_system.repository.TrainerProfileRepository;
import com.fourbitlabs.employee_management_system.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private AnalystProfileRepository analystProfile;
    @Autowired
    private TrainerProfileRepository trainerProfileRepository;

    @Override
    public BatchResponseDto saveBatch(BatchRequestDto batchRequestDto) {
        AnalystProfile analystProfiles = analystProfile.findById(batchRequestDto.getAnalystId())
                .orElseThrow(()->new ResourceNotFoundException("Analyst not found with this id: "+batchRequestDto.getAnalystId()));

        TrainerProfile trainerProfiles = trainerProfileRepository.findById(batchRequestDto.getTrainerId())
                .orElseThrow(()->new ResourceNotFoundException("Trainer not found with this id: "+batchRequestDto.getTrainerId()));

        Batch batch = new Batch();
        batch.setName(batchRequestDto.getName());
        batch.setCourse(batchRequestDto.getCourse());
        batch.setStartDate(batchRequestDto.getStartDate());
        batch.setEndDate(batchRequestDto.getEndDate());
        batch.setAnalyst(analystProfiles);
        batch.setTrainer(trainerProfiles);
        batch.setStatus(BatchStatus.ACTIVE);
        Batch savedBatch = batchRepository.save(batch);

        return getBatchResponseDto(savedBatch);
    }

    @NotNull
    private static BatchResponseDto getBatchResponseDto(Batch savedBatch) {
        BatchResponseDto batchResponseDto = new BatchResponseDto();
        batchResponseDto.setId(savedBatch.getId());
        batchResponseDto.setName(savedBatch.getName());
        batchResponseDto.setCourse(savedBatch.getCourse());
        batchResponseDto.setStatus(savedBatch.getStatus());
        batchResponseDto.setStartDate(savedBatch.getStartDate());
        batchResponseDto.setEndDate(savedBatch.getEndDate());
        batchResponseDto.setAnalystId(savedBatch.getAnalyst().getId());
        batchResponseDto.setTrainerId(savedBatch.getTrainer().getId());
        return batchResponseDto;
    }
}
