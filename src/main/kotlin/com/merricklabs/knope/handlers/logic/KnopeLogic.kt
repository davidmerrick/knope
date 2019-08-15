package com.merricklabs.knope.handlers.logic

import com.amazonaws.services.lambda.runtime.events.S3Event
import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.knope.external.aws.KnopeS3Client
import com.merricklabs.knope.external.sns.SnsNotifier
import com.merricklabs.knope.models.verizon.VerizonBill
import com.merricklabs.knope.util.BillSplitter
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject

private val log = KotlinLogging.logger {}

class KnopeLogic : KoinComponent {

    private val mapper by inject<ObjectMapper>()
    private val s3Client by inject<KnopeS3Client>()
    private val notifier by inject<SnsNotifier>()

    fun handleRequest(event: SNSEvent) {
        log.info("Received event: ${mapper.writeValueAsString(event)}")

        val s3Event = mapper.readValue(event.records[0].sns.message, S3Event::class.java)
        val billFile = s3Client.getBill(s3Event.records[0].s3.`object`.key)
        log.info("Success: Fetched bill from bucket")
        val bill = mapper.readValue(billFile.objectContent, VerizonBill::class.java)

        val splitConfig = s3Client.getBillSplitConfig()
        log.info("Success: Fetched config from bucket")
        val billSplitter = BillSplitter(splitConfig, bill)

        val billsPerPerson = splitConfig.groups
                .asSequence()
                .map { it.name to billSplitter.getGroupTotal(it.items) }
                .toMap()

        val message = "Bill split:\n${billsPerPerson.map { "${it.key}: $${"%.2f".format(it.value.getTotal())}" }.joinToString(separator = "\n")}"
        println(message)
        notifier.notify(message)
    }
}