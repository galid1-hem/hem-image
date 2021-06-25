package com.galid.uploader.infrastructure

import com.galid.uploader.util.IDGenerator
import org.springframework.stereotype.Component
import java.util.*

@Component
class DynamoDBIDGenerator: IDGenerator<String>{
    override fun getNewId(): String {
        return UUID.randomUUID().toString()
    }
}