package net.slipp

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

@SpringBootApplication
class Application : AbstractReactiveMongoConfiguration() {
    override fun reactiveMongoClient(): MongoClient = MongoClients.create()
    override fun getDatabaseName(): String = "todo"
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
