package com.api.arithmeticcalculator.repository;

import com.api.arithmeticcalculator.models.OperationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<OperationModel, UUID> {

    @Transactional
    Integer deleteByType(String type);

    @Transactional
    OperationModel getOperationByType(String type);

}
