package com.mkksoftware.drugrecord.service;

import com.mkksoftware.drugrecord.model.DrugRecordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrugRecordServiceTest {

    @InjectMocks
    private DrugRecordService drugRecordService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(drugRecordService, "openFdaApiUrl", "https://api.open.fda.gov/drug/drugsfda.json");
    }

    @Test
    void testSearchDrugs() {
        String manufacturer = "Pfizer";
        String brandName = "Lipitor";

        DrugRecordResponse mockResponse = new DrugRecordResponse();

        when(restTemplate.getForObject(anyString(), eq(DrugRecordResponse.class))).thenReturn(mockResponse);

        DrugRecordResponse response = drugRecordService.searchDrugs(manufacturer, brandName, 1,5);

        assertNotNull(response);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(DrugRecordResponse.class));
    }
}