package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BillBody(
        @JsonProperty("header") val header: String,
        @JsonProperty("label") val label: String,
        @JsonProperty("items") val items: List<BillItem>
)