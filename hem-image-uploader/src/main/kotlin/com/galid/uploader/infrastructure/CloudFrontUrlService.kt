package com.galid.uploader.infrastructure

import com.galid.uploader.application.UrlService
import com.galid.uploader.domain.entity.ImageEntity.*
import com.galid.uploader.domain.entity.ImageEntity.ImageType.POST
import com.galid.uploader.domain.entity.ImageEntity.ImageType.PROFILE
import com.galid.uploader.domain.entity.ImageEntity.Size.*
import org.springframework.stereotype.Service

@Service
class CloudFrontUrlService : UrlService {
    override fun makeUrls(
        sizes: List<Size>,
        filePath: String,
        serviceType: ServiceType,
        imageType: ImageType,
    ): Map<String, String> {
        // CDN 경로, parameters(size, 압축타입)
        val basicUrl = "${cdnHost["kr"]}/${serviceType.value}/$filePath"

        return sizes.map {
            it.value to makeUrlPerSize(
                basicUrl = basicUrl,
                imageType = imageType,
                size = it
            )
        }.toMap()
    }

    internal fun makeUrlPerSize(
        basicUrl: String,
        imageType: ImageType,
        size: Size,
    ): String {
        val resizeInfos = resizeInfo[imageType]!![size]!!
        val typeParam = resizeInfos[RESIZE_OPTION_TYPE]
        val qualityParam = resizeInfos[RESIZE_OPTION_QUALITY]
        val sizeParam = resizeInfos[RESIZE_OPTION_SIZE]
        return "$basicUrl?type=${typeParam}&quality=${qualityParam}&size=${sizeParam}x${sizeParam}"
    }

    companion object {
        // cdn_position: domain
        val cdnHost = mapOf(
            "kr" to "https://da226rldzsnn6.cloudfront.net"
        )

        val RESIZE_OPTION_SIZE = "size"
        val RESIZE_OPTION_TYPE = "type"
        val RESIZE_OPTION_QUALITY = "quality"

        val resizeInfo = mapOf(
            PROFILE to mapOf(
                SMALL to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                ),
                MEDIUM to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                ),
                LARGE to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                )
            ),
            POST to mapOf(
                SMALL to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                ),
                MEDIUM to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                ),
                LARGE to mapOf(
                    RESIZE_OPTION_SIZE to "10",
                    RESIZE_OPTION_TYPE to "crop",
                    RESIZE_OPTION_QUALITY to "80"
                )
            )
        )
    }
}