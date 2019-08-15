package com.merricklabs.knope.config

import com.google.common.io.Resources
import com.merricklabs.knope.IntegrationTestBase
import com.merricklabs.knope.util.ConfigObjectMapper
import io.kotlintest.shouldNotBe
import org.koin.test.inject
import org.testng.annotations.Test

class KnopeConfigTest : IntegrationTestBase() {

    private val mapper by inject<ConfigObjectMapper>()

    @Test
    fun `Parse yaml into config`() {
        val config = mapper.readValue(Resources.getResource("config.yaml"), SplitConfig::class.java)
        config shouldNotBe null
    }
}
