package br.dev.ardc.domain

import java.util.UUID

class Journey (
    val id: UUID,
    val name: String,
    val description: String,
    val badges: List<BadgeBase>)
{
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    /**
     * Adds a new badge to the journey.
     * @param newBadge the badge to be added
     */
    fun expandJourney(newBadge: BadgeBase): Journey {
        return Journey(
            id = this.id,
            name = this.name,
            description = this.description,
            badges = this.badges + newBadge
        )
    }

    /**
     * Removes an existing badge from a journey by its title.
     * @param badgeTitle the title of the badge to be removed
     */
    fun shrinkJourney(badgeTitle: String): Journey {
        require(badgeTitle.isNotBlank()) { "Badge title must not be blank" }
        return Journey(
            id = this.id,
            name = this.name,
            description = this.description,
            badges = this.badges.filter { it.title != badgeTitle }
        )
    }
}