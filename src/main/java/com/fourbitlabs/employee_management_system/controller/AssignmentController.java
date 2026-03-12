package com.fourbitlabs.employee_management_system.controller;

import org.springframework.web.bind.annotation.*;

/**
 * AssignmentController handles student-to-batch assignment operations.
 * 
 * Responsibilities:
 * - Assign a student to a batch
 * - Transfer a student between batches
 * - Get all students in a specific batch
 * 
 * Note: Assignment service methods are not yet implemented.
 *       Endpoints will be added here once the service layer supports them.
 * 
 * Planned endpoints:
 * - POST /api/assignments               → Assign student to batch
 * - PUT  /api/assignments/transfer       → Transfer student to another batch
 * - GET  /api/assignments/batch/{batchId} → Get students in a batch
 */
@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    // Assignment endpoints will be added here once
    // the corresponding service methods are available.
}
