package com.merricklabs.knope.handlers.logic

import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import com.merricklabs.knope.IntegrationTestBase
import org.koin.test.get
import org.testng.annotations.Test

class KnopeLogicTest : IntegrationTestBase() {

    @Test()
    fun `Test logic`() {
        val logic = get<KnopeLogic>()
        val mapper = get<ObjectMapper>()
        val event = mapper.readValue(Resources.getResource("event.json"), SNSEvent::class.java)

        logic.handleRequest(event)
    }
}
