package com.merricklabs.knope.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.merricklabs.knope.KnopeModule
import com.merricklabs.knope.handlers.logic.KnopeLogic
import org.koin.core.context.startKoin

class SnsHandler : RequestHandler<SNSEvent, Unit> {

    private val logic: KnopeLogic

    init {
        startKoin {
            modules(KnopeModule)
        }

        logic = KnopeLogic()
    }

    override fun handleRequest(input: SNSEvent, context: Context) {
        return logic.handleRequest(input)
    }
}