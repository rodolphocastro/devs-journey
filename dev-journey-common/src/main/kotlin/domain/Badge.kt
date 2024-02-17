package br.dev.ardc.domain

import java.time.LocalDate
import java.util.UUID

/**
 * A badge represents an achievement or a milestone that a dev may earn on its Journey.
 * @param id the unique identifier of the badge
 * @param title the title of the badge
 * @param description the description of the badge, may be blank
 * @param earnedAt the date when the badge was earned, cannot be in the future
 */
data class Badge(
    val id: UUID,
    val title: String,
    val description: String,
    val earnedAt: LocalDate
) {
    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
        require(earnedAt.isBefore(LocalDate.now().plusDays(1))) { "Date cannot be in the future" }
    }

    companion object {
        /**
         * Creates a new badge with a random UUID
         * @param title the title of the badge
         * @param description the description of the badge
         * @param earnedAt the date when the badge was earned
         */
        fun create(title: String, description: String, earnedAt: LocalDate): Badge {
            return Badge(
                UUID.randomUUID(),
                title,
                description,
                earnedAt
            )
        }
    }
}
