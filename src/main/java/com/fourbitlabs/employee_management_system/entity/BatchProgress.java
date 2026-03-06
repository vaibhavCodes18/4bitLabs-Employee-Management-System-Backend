package com.fourbitlabs.employee_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "batch_progress")
@Data
public class BatchProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String documentName;

    private String documentData;

    private Integer sessionNumber;

    private String topicCovered;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerProfile trainer;
}
