package com.fourbitlabs.employee_management_system.service.interfaces;

import com.fourbitlabs.employee_management_system.dto.request.BatchProgressRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.TrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.request.UpdateTrainerRequestDto;
import com.fourbitlabs.employee_management_system.dto.response.BatchProgressResponseDto;
import com.fourbitlabs.employee_management_system.dto.response.TrainerResponseDto;

import java.util.List;

public interface TrainerService {
    TrainerResponseDto createTrainer(TrainerRequestDto trainerRequestDto);
    List<TrainerResponseDto> getAllTrainers();
    TrainerResponseDto getTrainerById(Long userId);
    TrainerResponseDto updateTrainer(Long userId, UpdateTrainerRequestDto updateDto);
    void deleteTrainer(Long userId);
    BatchProgressResponseDto addBatchProgress(BatchProgressRequestDto batchProgressRequestDto);
    List<BatchProgressResponseDto> getBatchProgress(Long batchId);
}
