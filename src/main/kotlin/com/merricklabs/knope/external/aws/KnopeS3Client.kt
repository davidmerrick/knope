package com.merricklabs.knope.external.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.S3Object
import com.merricklabs.knope.config.KnopeConfig
import com.merricklabs.knope.config.SplitConfig
import com.merricklabs.knope.util.ConfigObjectMapper
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class KnopeS3Client : KoinComponent {
    protected val config by inject<KnopeConfig>()
    private val mapper by inject<ConfigObjectMapper>()

    protected abstract val s3: AmazonS3

    fun getBill(fileName: String): S3Object = s3.getObject(config.s3.bucketName, fileName)
    fun getBillSplitConfig(): SplitConfig {
        val configObject = s3.getObject(config.s3.configBucketName, KnopeConfig.S3.CONFIG_FILENAME)
        return mapper.readValue(configObject.objectContent, SplitConfig::class.java)
    }
}