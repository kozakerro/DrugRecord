package com.mkksoftware.drugrecord.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DrugRecord {
    @JsonProperty("application_number")
    private List<String> applicationNumber;
    @JsonProperty("manufacturer_name")
    private List<String> manufacturerName;
    @JsonProperty("substance_name")
    private List<String> substanceName;
    @JsonProperty("product_ndc")
    private List<String> productNumbers;
}
