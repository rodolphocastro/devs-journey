package domain

import br.dev.ardc.domain.Developer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import java.util.*
import kotlin.test.Test

class DeveloperTest {
    lateinit var subject: Developer

    @Test
    fun `when creating a developer the name should not be empty`(): Unit {
        assertThatThrownBy {
            // Act
            Developer(
                id = UUID.randomUUID(), name = "", email = "an_email@domain.com"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `when creating a developer the email should not be empty`(): Unit {
        assertThatThrownBy {
            // Act
            Developer(
                id = UUID.randomUUID(), name = "A name", email = ""
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `the create method should automatically provide an ID for a Developer`(): Unit {
        // Arrange
        // Act
        val developer = Developer.create(name = "A name", email = "an_email@domain.com")
        // Assert
        assertThat(developer.id).isNotNull()
    }
}