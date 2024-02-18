package domain

import br.dev.ardc.domain.entities.Badge
import br.dev.ardc.domain.entities.Developer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.util.*
import kotlin.test.Test

class DeveloperTest {
    private lateinit var subject: Developer

    @Test
    fun `when creating a developer the name should not be empty`(): Unit {
        assertThatThrownBy {
            // Act
            subject = Developer(
                id = UUID.randomUUID(), name = "", email = "an_email@domain.com"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `when creating a developer the email should not be empty`(): Unit {
        assertThatThrownBy {
            // Act
            subject = Developer(
                id = UUID.randomUUID(), name = "A name", email = ""
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `the create method should automatically provide an ID for a Developer`(): Unit {
        // Arrange
        // Act
        subject = Developer.create(name = "A name", email = "an_email@domain.com")
        // Assert
        assertThat(subject.id).isNotNull()
    }

    @Test
    fun `granting a badge creates a new badge instance`(): Unit {
        // Arrange
        subject = Developer(
            id = UUID.randomUUID(), name = "A name", email = "dev_mail@domain.com")

        // Act
        val badge = subject.grantBadge(
            Badge(title = "A badge title", description = "A badge description")
        )

        // Assert
        assertThat(badge).isNotNull()
        assertThat(badge.assignedToUser).isEqualTo(subject.id)
    }
}