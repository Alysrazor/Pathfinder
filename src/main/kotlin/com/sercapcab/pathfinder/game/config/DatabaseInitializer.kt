package com.sercapcab.pathfinder.game.config

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

private const val DEBUG = false

@Component
class DatabaseInitializer(private val entityManager: EntityManager) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {

        if (DEBUG) {
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate()
            entityManager.createNativeQuery("TRUNCATE TABLE unit_template").executeUpdate()
            entityManager.createNativeQuery("TRUNCATE TABLE unit_stat_template").executeUpdate()
            entityManager.createNativeQuery("TRUNCATE TABLE spell_template").executeUpdate()
            entityManager.createNativeQuery("TRUNCATE TABLE unit_spells").executeUpdate()
            entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate()
        }
    }
}