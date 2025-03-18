package com.mkksoftware.drugrecord.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DrugRecordEntity {

    @Id
    private String applicationNumber;
    @ElementCollection
    private List<String> manufacturerName;
    @ElementCollection
    private List<String> substanceName;
    @ElementCollection
    private List<String> productNumbers;

}