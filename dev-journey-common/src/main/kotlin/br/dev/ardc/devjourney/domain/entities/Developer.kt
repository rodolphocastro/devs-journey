package br.dev.ardc.devjourney.domain.entities

import java.time.LocalDate
import java.util.*

/**
 * A developer represents a person that is part of the community
 * @param id the unique identifier of the developer
 * @param name the name of the developer
 * @param email the email of the developer
 */
data class Developer(
    val id: UUID,
    val name: String,
    val email: String
) {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
        require(email.isNotBlank()) { "Email must not be blank" }
    }

    /**
     * Grants a badge to the developer.
     */
    fun grantBadge(badge: Badge): EarnedBadge {
        return EarnedBadge.create(badge.title, this.id, badge.description, LocalDate.now())
    }

    companion object {
        /**
         * Creates a new developer with a random UUID.
         */
        fun create(name: String, email: String): Developer {
            return Developer(UUID.randomUUID(), name, email)
        }
    }
}
