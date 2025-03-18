package com.mkksoftware.drugrecord.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrugResult {
    @JsonProperty("openfda")
    private DrugRecord openFDA;

    public DrugRecord getOpenFDA() {
        return this.openFDA;
    }
}
