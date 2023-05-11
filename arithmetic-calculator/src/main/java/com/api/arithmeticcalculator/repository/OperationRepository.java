package com.api.arithmeticcalculator.repository;

import com.api.arithmeticcalculator.models.OperationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<OperationModel, UUID> {

    @Transactional
    Integer deleteByType(String type);

    @Transactional
    Optional<OperationModel> getOperationByType(String type);

}
