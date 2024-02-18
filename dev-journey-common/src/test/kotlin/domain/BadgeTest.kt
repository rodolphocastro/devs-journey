package domain

import br.dev.ardc.domain.Badge
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class BadgeTest {
    lateinit var subject: Badge
    
    @Test
    fun `a badge's title should never be empty`(): Unit {
        // Arrange
        // Assert
        assertThatThrownBy{
            // Act
            subject = Badge(
                id = UUID.randomUUID(),
                assignedToUser = UUID.randomUUID(),
                title = "",
                description = "A badge description",
                earnedAt = LocalDate.now()
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `a badge's earnedAt should never be in the future`(): Unit {
        // Arrange
        // Assert
        assertThatThrownBy{
            // Act
            subject = Badge(
                id = UUID.randomUUID(),
                assignedToUser = UUID.randomUUID(),
                title = "A badge title",
                description = "A badge description",
                earnedAt = LocalDate.now().plusDays(1)
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `creating a badge from the create function should autopopulate the ID`(): Unit {
        // Arrange
        // Act
        subject = Badge.create(
            title = "A badge title",
            assignedToUser = UUID.randomUUID(),
            description = "A badge description",
            earnedAt = LocalDate.now()
        )
        // Assert
        assertThat(subject.id).isNotNull()
    }
}