package com.marcos

import com.marcos.data.models.MongoUserDataSource
import com.marcos.data.models.User
import com.marcos.plugins.configureMonitoring
import com.marcos.plugins.configureRouting
import com.marcos.plugins.configureSecurity
import com.marcos.plugins.configureSerialization
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


// Funcion que se llama cuando se lanza el programa
fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPw = System.getenv("MONGO_PW") // clave de la conecciónn como variable de entorno
    val dbName ="pruebaKtorJwt"

    val db = KMongo.createClient(
        connectionString = "mongodb+srv://mculzoni:$mongoPw@pruebaktorjwt.vfoeb5c.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine
        .getDatabase(dbName)

    val userDataSource= MongoUserDataSource(db=db)

    GlobalScope.launch {
        val user = User(
            username = "test",
            password = "test-password",
            salt="salt"
        )

        userDataSource.insertUser(user)


    }

    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
