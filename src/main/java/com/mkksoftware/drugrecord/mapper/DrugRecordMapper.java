package com.mkksoftware.drugrecord.mapper;

import com.mkksoftware.drugrecord.model.DrugRecord;
import com.mkksoftware.drugrecord.model.DrugRecordEntity;

import java.util.Collections;

public class DrugRecordMapper {

    public static DrugRecord map(DrugRecordEntity drugRecordEntity) {
        return DrugRecord.builder()
                .applicationNumber(Collections.singletonList(drugRecordEntity.getApplicationNumber()))
                .manufacturerName(drugRecordEntity.getManufacturerName())
                .substanceName(drugRecordEntity.getSubstanceName())
                .productNumbers(drugRecordEntity.getProductNumbers())
                .build();
    }

}