package br.dev.ardc.devjourney.domain.entities

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import java.util.*

class JourneyTest {
    private lateinit var subject: Journey

    @Test
    fun `when creating a Journey the name should not be empty`(): Unit {
        // Arrange
        // Act
        assertThatThrownBy {
            subject = Journey(
                id = UUID.randomUUID(),
                name = "",
                description = "A description",
                badges = listOf(),
                subscribers = setOf()
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `when a journey is expanded with a new Badge a new Journey should be returned`(): Unit {
        // Arrange
        subject = Journey(
            id = UUID.randomUUID(),
            name = "A journey",
            description = "A description",
            badges = listOf(),
            subscribers = setOf()
        )

        // Act
        subject.apply {
            val badge: Badge = mock()
            val got = expandJourney(badge)

            // Assert
            assertThat(badges).isEmpty()
            assertThat(got).isNotNull()
            assertThat(got.badges).isNotEmpty()
        }
    }

    @Test
    fun `when a journey is shrunken with a badge a new Journey should be returned without the given badge`(): Unit {
        // Arrange
        val knownTitle = "A badge title"
        subject = Journey(
            id = UUID.randomUUID(), name = "A journey", description = "A description", badges = listOf(
                Badge(title = knownTitle, description = "A badge description")
            ), setOf()
        )

        // Act
        subject.apply {
            val got = shrinkJourney(knownTitle)

            // Assert
            assertThat(badges).isNotEmpty()
            assertThat(got).isNotNull()
            assertThat(got.badges).isEmpty()
        }
    }

    @Test
    fun `when adding a subscriber a new entry should be added to the set`(): Unit {
        // Arrange
        val newSubscriber = UUID.randomUUID()
        subject = Journey(
            UUID.randomUUID(),
            "journey",
            "",
            listOf(),
            setOf()
        )

        // Act
        subject.apply {
            val got = addSubscriber(newSubscriber)

            // Assert
            assertThat(subscribers).isEmpty()
            assertThat(got.subscribers).isNotEmpty()
            assertThat(got.subscribers).contains(newSubscriber)
        }
    }

    @Test
    fun `when removing a subscriber an entry should be removed from the set`(): Unit {
        // Arrange
        val knownId = UUID.randomUUID()
        subject = Journey(
            UUID.randomUUID(),
            "journey",
            "",
            listOf(),
            setOf(knownId)
        )

        // Act
        subject.apply {
            val got = removeSubscriber(knownId)

            assertThat(subscribers).isNotEmpty()
            assertThat(got.subscribers).isEmpty()
        }
    }

    @Test
    fun `when shrinking a Journey a badge name is always required`(): Unit {
        // Arrange
        // Act
        assertThatThrownBy {
            subject = Journey(
                id = UUID.randomUUID(),
                name = "A journey",
                description = "A description",
                badges = listOf(),
                subscribers = setOf()
            ).shrinkJourney("")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}