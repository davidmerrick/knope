package com.merricklabs.knope

import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.knope.config.KnopeConfig
import com.merricklabs.knope.external.aws.KnopeS3Client
import com.merricklabs.knope.external.aws.KnopeS3ClientImpl
import com.merricklabs.knope.external.sns.SnsNotifier
import com.merricklabs.knope.external.sns.SnsNotifierImpl
import com.merricklabs.knope.handlers.logic.KnopeLogic
import com.merricklabs.knope.util.ConfigObjectMapper
import com.merricklabs.knope.util.KnopeObjectMapper
import org.koin.dsl.module

val KnopeModule = module {
    single { KnopeLogic() }
    single { KnopeConfig() }
    single { KnopeObjectMapper() as ObjectMapper }
    single { ConfigObjectMapper() }
    single { KnopeS3ClientImpl() as KnopeS3Client }
    single { SnsNotifierImpl() as SnsNotifier }
}