package com.merricklabs.knope.config

const val DEFAULT_REGION = "us-west-2"
const val DEFAULT_BUCKET_NAME = "wyatt-bills"
const val DEFAULT_CONFIG_BUCKET_NAME = "knope-config"

class KnopeConfig {

    val s3 = S3()
    val sns = SNS()

    class S3 {
        val bucketName = System.getenv("S3_BUCKET_NAME") ?: DEFAULT_BUCKET_NAME
        val configBucketName = System.getenv("CONFIG_BUCKET_NAME") ?: DEFAULT_CONFIG_BUCKET_NAME
        val region = System.getenv("AWS_REGION") ?: DEFAULT_REGION

        companion object {
            const val CONFIG_FILENAME = "config.yaml"
        }
    }

    class SNS {
        // Todo: Mock this class and make this param required
        val topicArn = System.getenv("SNS_TOPIC") ?: ""
    }
}