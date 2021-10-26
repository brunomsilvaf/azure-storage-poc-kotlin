package com.storage.azure.poc

import com.azure.core.credential.TokenCredential
import com.azure.identity.ClientSecretCredentialBuilder
import com.azure.storage.blob.BlobClient
import com.azure.storage.blob.BlobClientBuilder
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class AzureStorageClient(private val config: AzureStorageConfiguration) {

    private val overwriteFile = true

    fun getCredentials(): TokenCredential {
        return ClientSecretCredentialBuilder()
            .clientId(config.clientId)
            .clientSecret(config.clientSecret)
            .tenantId(config.tenantId)
            .build()
    }

    fun getBlobClient(url: String): BlobClient {
        return BlobClientBuilder()
            .endpoint(url)
            .credential(this.getCredentials())
            .buildClient()
    }

    fun uploadFromString(blobUrl: String, fileContent: String): String? {

        val blobClient = this.getBlobClient(blobUrl)

        ByteArrayInputStream(fileContent.toByteArray()).use {
                dataStream -> blobClient.upload(dataStream, fileContent.length.toLong(), overwriteFile)
        }

        return blobClient.blobUrl
    }

    fun uploadFromFile(blobUrl: String, fileUrl: String) {
        val blobClient = this.getBlobClient(blobUrl)
        blobClient.uploadFromFile(fileUrl, overwriteFile)
    }

}

