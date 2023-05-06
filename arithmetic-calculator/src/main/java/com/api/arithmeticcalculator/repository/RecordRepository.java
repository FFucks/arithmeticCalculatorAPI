package com.api.arithmeticcalculator.repository;

import com.api.arithmeticcalculator.models.RecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<RecordModel, UUID> {

}
