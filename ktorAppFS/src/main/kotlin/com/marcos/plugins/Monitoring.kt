package com.marcos.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.*



fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
        callIdMdc("call-id")
    }
    install(CallId) {
        header(HttpHeaders.XRequestId)
        verify { callId: String ->
            callId.isNotEmpty()
        }
    }
}
/*
El plugin configureMonitoring define la configuración de monitoreo para una aplicación Ktor. Este plugin instala dos
características principales: CallLogging y CallId.

1) CallLogging: Este plugin registra los detalles de las llamadas entrantes a la aplicación, incluyendo información sobre las solicitudes HTTP, como la ruta, el método HTTP, el tiempo de respuesta, etc. En este caso, se configura para registrar los detalles de las llamadas con un nivel de log de INFO y filtra las llamadas que comienzan con "/". También asigna un identificador único a cada llamada (call-id).

2) CallId: Este plugin es responsable de generar y gestionar un identificador único para cada llamada entrante. Aquí se configura para extraer el identificador de una cabecera HTTP específica (HttpHeaders.XRequestId) y se verifica que el identificador no esté vacío.

En resumen, el plugin de monitoreo (configureMonitoring) proporciona funcionalidades esenciales para registrar y rastrear las llamadas entrantes a la aplicación, lo que puede ser útil para el análisis de rendimiento, depuración y seguimiento de solicitudes en una aplicación Ktor.



 */