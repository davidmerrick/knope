package com.merricklabs.knope

import com.merricklabs.knope.external.aws.KnopeS3Client
import com.merricklabs.knope.external.sns.MockSnsNotifier
import com.merricklabs.knope.external.sns.SnsNotifier
import com.merricklabs.knope.mocks.MockKnopeS3Client
import com.merricklabs.knope.testutil.TestS3Client

import org.koin.dsl.module

val KnopeTestModule = module {
    single(override = true) { MockKnopeS3Client() as KnopeS3Client }
    single(override = true) { MockSnsNotifier() as SnsNotifier }
    single { TestS3Client() }
}