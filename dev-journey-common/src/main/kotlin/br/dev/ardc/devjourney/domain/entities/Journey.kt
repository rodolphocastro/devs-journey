package br.dev.ardc.devjourney.domain.entities

import java.util.UUID

/**
 * A journey represents a path that a developer may take to learn new skills and earn badges.
 * @param id the unique identifier of the journey
 * @param name the name of the journey
 * @param description the description of the journey, may be blank
 * @param badges the badges that are part of the journey
 * @param subscribers the UUID of users that have subscribed to this journey
 */
class Journey (
    val id: UUID,
    val name: String,
    val description: String,
    val badges: List<Badge>,
    val subscribers: Set<UUID>)
{
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    /**
     * Adds a new badge to the journey.
     * @param newBadge the badge to be added
     */
    fun expandJourney(newBadge: Badge): Journey {
        return Journey(
            id = this.id,
            name = this.name,
            description = this.description,
            badges = this.badges + newBadge,
            subscribers = this.subscribers
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
            badges = this.badges.filter { it.title != badgeTitle },
            subscribers = this.subscribers
        )
    }

    /**
     * Adds a subscriber to the journey.
     * @param userId user's id who's subscribing to the journey
     */
    fun addSubscriber(userId: UUID): Journey {
        return Journey(
            id = this.id,
            name = this.name,
            description = this.description,
            badges = this.badges,
            subscribers = this.subscribers + userId
        )
    }

    /**
     * Removes a subscriber for the journey.
     * @param userId user's id who's unsubscribing from the journey
     */
    fun removeSubscriber(userId: UUID): Journey {
        return Journey(
            this.id,
            this.name,
            this.description,
            this.badges,
            this.subscribers - userId
        )
    }
}