package com.api.arithmeticcalculator.controllers;

import com.api.arithmeticcalculator.dtos.RecordDto;
import com.api.arithmeticcalculator.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
@RestController
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<List<RecordDto>> getRecords(@PathVariable String id) {
        List<RecordDto> obj = recordService.getRecords(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @DeleteMapping("/record/{id}")
    public void removeRecord(@PathVariable String id) {
        recordService.removeRecord(UUID.fromString(id));
    }
}
