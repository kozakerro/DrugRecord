package com.mkksoftware.drugrecord.service;

import com.mkksoftware.drugrecord.mapper.DrugRecordEntityMapper;
import com.mkksoftware.drugrecord.mapper.DrugRecordMapper;
import com.mkksoftware.drugrecord.model.DrugRecord;
import com.mkksoftware.drugrecord.model.DrugRecordResponse;
import com.mkksoftware.drugrecord.model.DrugResult;
import com.mkksoftware.drugrecord.repository.DrugRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DrugRecordService {

    @Value("${openfda.api.url}")
    private String openFdaApiUrl;

    private final RestTemplate restTemplate;
    private final DrugRecordRepository drugRecordRepository;

    @Autowired
    public DrugRecordService(RestTemplate restTemplate, DrugRecordRepository drugRecordRepository) {
        this.restTemplate = restTemplate;
        this.drugRecordRepository = drugRecordRepository;
    }

    public DrugRecordResponse searchDrugs(String manufacturer, String brandName, int page, int size) {
        String searchString = "openfda.manufacturer_name:" + manufacturer;
        if (brandName != null) {
            searchString = searchString + ",openfda.brand_name:" + brandName;
        }

        String url = UriComponentsBuilder.fromUriString(openFdaApiUrl)
                .queryParam("search", searchString.toString())
                .queryParam("limit", size)
                .toUriString();

        System.out.println("url:" + url);

        return restTemplate.getForObject(url, DrugRecordResponse.class);
    }

    public DrugRecord saveDrugRecord(String applicationNumber) {
        String searchString = "openfda.application_number:" + applicationNumber;

        String url = UriComponentsBuilder.fromUriString(openFdaApiUrl)
                .queryParam("search", searchString.toString())
                .toUriString();

        System.out.println("url:" + url);

        DrugRecordResponse drug = restTemplate.getForObject(url, DrugRecordResponse.class);

        DrugRecord drugRecord = Optional.ofNullable(drug).map(DrugRecordResponse::getResults).map(drugResults -> drugResults.get(0)).map(DrugResult::getOpenFDA).orElse(null);

        if (drugRecord != null) {
            drugRecordRepository.save(DrugRecordEntityMapper.map(drugRecord));
            return drugRecord;
        }

        return null;

    }

    public DrugRecord getStoredDrug(String applicationNumber) {
        return drugRecordRepository.findByApplicationNumber(applicationNumber)
                .map(DrugRecordMapper::map)
                .orElse(null);
    }

    public List<DrugRecord> getAllStoredDrugs() {
        return StreamSupport.stream(drugRecordRepository.findAll().spliterator(), false).collect(Collectors.toList())
                .stream()
                .map(DrugRecordMapper::map)
                .collect(Collectors.toList());
    }
}