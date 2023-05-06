package com.api.arithmeticcalculator.services;

import com.api.arithmeticcalculator.models.OperationModel;
import com.api.arithmeticcalculator.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public OperationModel saveOperation(String type, Integer cost) {
        try {
            OperationModel operationModel = new OperationModel();
            operationModel.setType(type);
            operationModel.setCost(cost);

            return operationRepository.save(operationModel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<OperationModel> getAll() {
        return operationRepository.findAll();
    }

    public Integer deleteOperation(String type) {
        return operationRepository.deleteByType(type);
    }

    public Optional<OperationModel> getOperationById(UUID uuid) {
        return operationRepository.findById(uuid);
    }

    public OperationModel getOperationByType(String type) {
        return operationRepository.getOperationByType(type);
    }
}
