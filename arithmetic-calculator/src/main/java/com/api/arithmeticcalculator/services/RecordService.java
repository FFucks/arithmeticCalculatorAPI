package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.Commons;
import com.api.arithmeticcalculator.models.RecordModel;
import com.api.arithmeticcalculator.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordModel addRecord(RecordModel recordModel) {
        recordModel.setDate(Commons.getUtcNow());
        return recordRepository.save(recordModel);
    }

    public void removeRecord(UUID id) {
        recordRepository.deleteById(id);
    }
}
