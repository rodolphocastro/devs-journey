import br.dev.ardc.Main
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun mainLoads(): Unit {
        // Arrange
        // Act
        // Assert
        assertThat(Main()).isNotNull()
    }
}