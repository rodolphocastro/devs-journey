package domain

import br.dev.ardc.domain.BadgeBase
import br.dev.ardc.domain.Journey
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
                id = UUID.randomUUID(), name = "", description = "A description", badges = listOf()
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }


    @Test
    fun `when a journey is expanded with a new Badge a new Journey should be returned`(): Unit {
        // Arrange
        subject = Journey(
            id = UUID.randomUUID(), name = "A journey", description = "A description", badges = listOf()
        )

        // Act
        subject.apply {
            val badge: BadgeBase = mock()
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
                BadgeBase(title = knownTitle, description = "A badge description")
            )
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
}