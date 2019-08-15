package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LineLevelDetails(
        @JsonProperty("header") val header: String,
        @JsonProperty("mtn") val mtn: String,
        @JsonProperty("items") val items: List<BillItem>
)