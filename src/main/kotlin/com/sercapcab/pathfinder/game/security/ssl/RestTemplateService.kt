package com.sercapcab.pathfinder.game.security.ssl

import org.springframework.boot.ssl.SslBundles
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RestTemplateService(restTemplateBuilder: RestTemplateBuilder, sslBundles: SslBundles) {
    private var restTemplate: RestTemplate? = null

    init {
        this.restTemplate = restTemplateBuilder.setSslBundle(sslBundles.getBundle("myBundle")).build()
    }
}