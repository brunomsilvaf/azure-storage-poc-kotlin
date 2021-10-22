package com.storage.azure.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
@EnableConfigurationProperties(AzureStorageConfiguration::class)
class AzureStorageApplication

fun main(args: Array<String>) {
    val context = runApplication<AzureStorageApplication>(*args)
    val azureStorageClient = context.getBean(AzureStorageClient::class.java)

    //azureStorageClient.uploadBlob("test.txt", "Creating a file")
    azureStorageClient.uploadSamples();
}
