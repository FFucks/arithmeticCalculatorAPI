package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.Commons;
import com.api.arithmeticcalculator.dtos.RecordDto;
import com.api.arithmeticcalculator.models.RecordModel;
import com.api.arithmeticcalculator.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

    public List<RecordModel> getAllRecords() {
        return recordRepository.findAll();
    }

    public List<RecordDto> getRecords(String userId) {

        List<Object[]> result = recordRepository.findRecordsAndOperation(UUID.fromString(userId));
        List<RecordDto> recordDtos = new ArrayList<>();
        for (Object[] row : result) {
            RecordDto recordDto = new RecordDto();
            recordDto.setId(UUID.fromString(row[0].toString()));
            recordDto.setOperationId(UUID.fromString(row[1].toString()));
            recordDto.setUserId(UUID.fromString(row[2].toString()));
            recordDto.setAmount((Integer) row[3]);
            recordDto.setUserBalance((Integer) row[4]);
            recordDto.setOperationResponse(new BigDecimal(row[5].toString()));
            recordDto.setDate(row[6].toString());
            recordDto.setType(row[7].toString());
            recordDtos.add(recordDto);
        }

        return recordDtos;
    }


    public void removeRecord(UUID id) {
        recordRepository.deleteById(id);
    }
}
