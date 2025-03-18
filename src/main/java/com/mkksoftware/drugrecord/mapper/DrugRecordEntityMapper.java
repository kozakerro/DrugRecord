package com.mkksoftware.drugrecord.mapper;

import com.mkksoftware.drugrecord.model.DrugRecord;
import com.mkksoftware.drugrecord.model.DrugRecordEntity;

public class DrugRecordEntityMapper {

    public static DrugRecordEntity map(DrugRecord drugRecord) {
        return DrugRecordEntity.builder()
                .applicationNumber(drugRecord.getApplicationNumber().get(0))
                .manufacturerName(drugRecord.getManufacturerName())
                .substanceName(drugRecord.getSubstanceName())
                .productNumbers(drugRecord.getProductNumbers())
                .build();
    }

}