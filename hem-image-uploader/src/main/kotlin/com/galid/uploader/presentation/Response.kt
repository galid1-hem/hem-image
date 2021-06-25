package com.galid.uploader.presentation

data class Response<T>(
    val status: Int? = 200,
    val message: String = "OK",
    val data: T? = null,
)