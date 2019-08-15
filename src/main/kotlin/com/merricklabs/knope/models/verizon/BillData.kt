package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BillData(
        @JsonProperty("changes_new") val changes_new: ChangesNew,
        @JsonProperty("bill") val bill: BillMeta,
        @JsonProperty("lineLevelDetails") val lineLevelDetails: List<LineLevelDetails>,
        @JsonProperty("accountSummaryDetails") val accountSummaryDetails: List<BillItem>
)