package com.api.arithmeticcalculator.repository;

import com.api.arithmeticcalculator.models.RecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<RecordModel, UUID> {

    @Query("SELECT r.id, r.operationId, r.userId, r.amount, r.userBalance, r.operationResponse, r.date, o.type as type " +
            "FROM RecordModel r " +
            "JOIN OperationModel o " +
            "ON r.operationId = o.id " +
            "WHERE r.userId = :userId " +
            "ORDER BY r.date DESC")
    List<Object[]> findRecordsAndOperation(@Param("userId") UUID userId);

}
