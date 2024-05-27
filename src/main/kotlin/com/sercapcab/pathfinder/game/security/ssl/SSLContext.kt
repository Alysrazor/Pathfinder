package com.sercapcab.pathfinder.game.security.ssl

import org.springframework.boot.ssl.SslBundle
import org.springframework.boot.ssl.SslBundles
import org.springframework.stereotype.Component
import javax.net.ssl.SSLContext

@Component
class SSLComponent(sslBundles: SslBundles) {
    init {
        val sslBundle: SslBundle = sslBundles.getBundle("client")
        val sslContext: SSLContext = sslBundle.createSslContext()
    }
}