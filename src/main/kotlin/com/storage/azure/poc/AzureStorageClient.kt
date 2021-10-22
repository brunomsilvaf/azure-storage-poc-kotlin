package com.storage.azure.poc

import com.azure.core.credential.TokenCredential
import com.azure.identity.ClientSecretCredentialBuilder
import com.azure.storage.blob.BlobClient
import com.azure.storage.blob.BlobClientBuilder
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@Component
class AzureStorageClient(private val config: AzureStorageConfiguration) {

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
            .credential(getCredentials())
            .buildClient()
    }

    fun uploadBlob(
        blobName: String,
        body: String
    ): String? {

        val blobClient = getBlobClient(config.url + "/" + blobName)
        try {
            ByteArrayInputStream(body.toByteArray()).use { dataStream ->
                blobClient.upload(
                    dataStream,
                    body.length.toLong(),
                    true
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return blobClient.blobUrl
    }

   fun uploadSamples() {
       var uri = this.javaClass.classLoader.getResource("samples").toURI()
       var path = Paths.get(uri)
       Files.list(path).forEach {
           val blobClient = getBlobClient(config.url + "/" + it.fileName)
           blobClient.uploadFromFile(it.toString())
       }
   }

}

