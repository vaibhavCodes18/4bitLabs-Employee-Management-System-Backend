package com.fourbitlabs.employee_management_system.dto.response;

import java.time.LocalDateTime;

public class BatchProgressResponseDto {
    private Long id;
    private String title;
    private String description;
    private Integer sessionNumber;
    private String topicCovered;
    private Long batchId;
    private Long trainerId;
    private LocalDateTime createdAt;

    public BatchProgressResponseDto() {
    }

    public BatchProgressResponseDto(Long id, String title, String description, Integer sessionNumber,
                                    String topicCovered, Long batchId, Long trainerId,
                                    LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionNumber = sessionNumber;
        this.topicCovered = topicCovered;
        this.batchId = batchId;
        this.trainerId = trainerId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getTopicCovered() {
        return topicCovered;
    }

    public void setTopicCovered(String topicCovered) {
        this.topicCovered = topicCovered;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerName(Long trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
