package com.mkksoftware.drugrecord.controller;

import com.mkksoftware.drugrecord.model.DrugRecord;
import com.mkksoftware.drugrecord.model.DrugRecordResponse;
import com.mkksoftware.drugrecord.model.DrugResult;
import com.mkksoftware.drugrecord.service.DrugRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drug-records")
public class DrugRecordController {

    private final DrugRecordService drugRecordService;

    @Autowired
    public DrugRecordController(DrugRecordService drugRecordService) {
        this.drugRecordService = drugRecordService;
    }

    @GetMapping("/search")
    public List<DrugRecord> searchDrugRecords(@RequestParam String manufacturerName,
                                           @RequestParam(required = false) String brandName,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "5") int size) {
        DrugRecordResponse drugRecordResponse = drugRecordService
                .searchDrugs(manufacturerName, brandName, page, size);
        return drugRecordResponse.getResults().stream()
                .map(DrugResult::getOpenFDA)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/{applicationNumber}")
    public DrugRecord createDrugRecord(@PathVariable String applicationNumber) {
        return drugRecordService.saveDrugRecord(applicationNumber);
    }

    @GetMapping(path = "/{applicationNumber}")
    public DrugRecord getDrugRecord(@PathVariable String applicationNumber) {
        return drugRecordService.getStoredDrug(applicationNumber);
    }

    @GetMapping
    public List<DrugRecord> getAllDrugRecords() {
        return drugRecordService.getAllStoredDrugs();
    }
}