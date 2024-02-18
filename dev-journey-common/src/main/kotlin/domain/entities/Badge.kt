package br.dev.ardc.domain.entities

import java.time.LocalDate
import java.util.UUID

/**
 * Contract that a badge must implement.
 */
interface IBadge {
    val title: String
    val description: String
}

/**
 * A badge base represents an achievement or a milestone that a dev may earn on its Journey.
 * @param title the title of the badge
 * @param description the description of the badge, may be blank
 */
data class Badge(override val title: String, override val description: String) : IBadge

/**
 * A badge represents an achievement or a milestone that a dev has earned on its Journey.
 * @param id the unique identifier of the badge
 * @param title the title of the badge
 * @param description the description of the badge, may be blank
 * @param earnedAt the date when the badge was earned, cannot be in the future
 */
class EarnedBadge(
    val id: UUID,
    val assignedToUser: UUID,
    override val title: String,
    override val description: String,
    val earnedAt: LocalDate,
) : IBadge {
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
        fun create(title: String, assignedToUser:UUID, description: String, earnedAt: LocalDate): EarnedBadge {
            return EarnedBadge(
                UUID.randomUUID(),
                assignedToUser,
                title,
                description,
                earnedAt
            )
        }
    }
}
