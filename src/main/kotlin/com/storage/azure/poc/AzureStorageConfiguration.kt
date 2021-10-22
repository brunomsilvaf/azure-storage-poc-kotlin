package com.storage.azure.poc

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("azure")
@ConstructorBinding
data class AzureStorageConfiguration(
    var url: String,
    var clientId: String,
    var tenantId: String,
    var clientSecret: String)
