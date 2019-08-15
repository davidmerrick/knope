package com.merricklabs.knope.models

data class GroupTotal(
        val personTotals: List<PersonTotal>,
        val taxes: Float,
        val surcharges: Float,
        val oneTimeCharges: Float,
        val dataSplit: Float
) {
    fun getTotal(): Float {
        val personTotals = personTotals.fold(0.0F) { sum, item -> sum + item.lineCost + item.devicePayment }
        return personTotals + taxes + surcharges + oneTimeCharges + dataSplit
    }
}