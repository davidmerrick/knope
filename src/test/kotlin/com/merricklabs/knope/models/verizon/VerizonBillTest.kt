package com.merricklabs.knope.models.verizon

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import com.merricklabs.knope.IntegrationTestBase
import com.merricklabs.knope.testutil.TestData.TEST_BILL
import io.kotlintest.shouldBe
import org.koin.test.inject
import org.testng.annotations.Test

class VerizonBillTest : IntegrationTestBase() {

    private val mapper by inject<ObjectMapper>()
    private val bill by lazy { mapper.readValue(Resources.getResource(TEST_BILL), VerizonBill::class.java) }

    @Test
    fun `Get taxes`() {
        testBillItem(4.30F) { bill.getTaxes() }
    }

    @Test
    fun `Get one-time charges`() {
        testBillItem(0.00F) { bill.getOneTimeFees() }
    }

    @Test
    fun `Get surcharges`() {
        testBillItem(9.75F) { bill.getSurcharges() }
    }
    
    private fun testBillItem(expectedValue: Float, totalSupplier: () -> Float) {
        val actualValue = totalSupplier.invoke()
        actualValue shouldBe expectedValue
    }
}
