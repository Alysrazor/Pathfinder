package com.sercapcab.pathfinder.game.security.ssl

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl
import org.springframework.stereotype.Service

@Service
class WebClientService(webClientBuilder: WebClientSsl) {
}