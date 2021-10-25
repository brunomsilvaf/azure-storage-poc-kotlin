package com.storage.azure.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AzureStorageConfiguration::class)
class AzureStorageApplication

fun main(args: Array<String>) {
    val context = runApplication<AzureStorageApplication>(*args)
    val azureService = context.getBean(AzureStorageService::class.java)

    //azureService.uploadSample("/qa/recordings/id-11a/", "sample1.mp3");
    //azureService.uploadSample("/qa/recordings/id-11a/", "sample2.mp3");
    //azureService.uploadSample("/qa/recordings/id-11b/", "sample3.mp3");
    //azureService.uploadBlob("/qa/recordings/id-11b/", "test.txt", "Creating a file")

    // override sample
    //azureService.overrideSample("/qa/recordings/id-11a/", "sample1.mp3")
}
