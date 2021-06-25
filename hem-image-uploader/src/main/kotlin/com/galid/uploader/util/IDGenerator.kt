package com.galid.uploader.util

interface IDGenerator<T> {
    fun getNewId(): T
}