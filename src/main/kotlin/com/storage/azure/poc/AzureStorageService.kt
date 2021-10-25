package com.storage.azure.poc

import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class AzureStorageService(
    private val azureConfig: AzureStorageConfiguration,
    private val azureClient: AzureStorageClient) {

    fun uploadBlob(path:String, blobName: String, body: String) {
        azureClient.uploadFromString(azureConfig.url + path + blobName, body)
    }

    fun uploadSample(path: String, sampleName: String) {
        val url = this.javaClass.classLoader.getResource("samples/$sampleName")
        val samplePath = Paths.get(url.toURI()).toString()
        azureClient.uploadFromFile(azureConfig.url + path + sampleName, samplePath)
    }

    fun overrideSample(path: String, sampleName: String) {
        val url = this.javaClass.classLoader.getResource("samples2/$sampleName")
        val samplePath = Paths.get(url.toURI()).toString()
        azureClient.uploadFromFile(azureConfig.url + path + sampleName, samplePath)
    }
}

