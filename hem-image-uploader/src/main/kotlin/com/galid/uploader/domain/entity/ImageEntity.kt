package com.galid.uploader.domain.entity

import java.time.LocalDateTime

class ImageEntity(
    var id: String? = null,
    val filePath: String, // S3 원본 이미지 경로
    val type: ImageType,
    var service: ServiceType? = null,
    var userId: Long? = null,
    var imageUrls: Map<String, String>,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) {
    enum class ImageType(val value: String) {
        PROFILE("profile"),
        POST("post"),
    }

    enum class ServiceType(val value: String) {
        POST("post"),
        AUTH("auth"),
    }

    enum class Size(val value: String) {
        SMALL("small"), MEDIUM("medium"), LARGE("large")
    }
}

// Image를 획득할 수 있는 url경로
//class ImageUrls(
//    val small: String? = null,
//    val medium: String? = null,
//    val large: String? = null,
//) {
//    enum class Size {
//        SMALL, MEDIUM, LARGE
//    }
//}