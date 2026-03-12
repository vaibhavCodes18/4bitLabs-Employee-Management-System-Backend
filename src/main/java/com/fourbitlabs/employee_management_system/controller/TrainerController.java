package com.fourbitlabs.employee_management_system.controller;

import org.springframework.web.bind.annotation.*;

/**
 * TrainerController handles all trainer-related operations.
 * 
 * The trainer is responsible for:
 * - Managing batch progress (add/view progress for assigned batches)
 * 
 * Note: Trainer creation is handled by the AdminController.
 * Note: Batch progress service methods are not yet implemented.
 *       Endpoints will be added here once the service layer supports them.
 * 
 * Planned endpoints:
 * - POST /api/trainer/batch-progress     → Add batch progress
 * - GET  /api/trainer/batch-progress/{batchId} → View batch progress by batch ID
 */
@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    // Batch progress endpoints will be added here once
    // the corresponding service methods are available.
}
