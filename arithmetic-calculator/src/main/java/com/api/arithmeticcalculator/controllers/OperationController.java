package com.api.arithmeticcalculator.controllers;

import com.api.arithmeticcalculator.models.OperationModel;
import com.api.arithmeticcalculator.services.OperationService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
@RestController
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/operation")
    public ResponseEntity<OperationModel> addOperation(@PathParam("type") String type,
                                                       @PathParam("cost") Integer cost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(operationService.saveOperation(type, cost));
    }

    @GetMapping("/operation")
    public ResponseEntity<List<OperationModel>> getAllOperations() {
        return ResponseEntity.status(HttpStatus.OK).body(operationService.getAll());
    }

    @DeleteMapping("/operation")
    public ResponseEntity<Integer> deleteOperation(String type) {
        return ResponseEntity.status(HttpStatus.OK).body(operationService.deleteOperation(type));
    }
}
