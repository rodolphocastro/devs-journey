package br.dev.ardc.devjourney.domain.entities

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