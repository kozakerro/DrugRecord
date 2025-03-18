package com.mkksoftware.drugrecord.repository;

import com.mkksoftware.drugrecord.model.DrugRecordEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DrugRecordRepository extends CrudRepository<DrugRecordEntity, String> {
    Optional<DrugRecordEntity> findByApplicationNumber(String applicationNumber);
}