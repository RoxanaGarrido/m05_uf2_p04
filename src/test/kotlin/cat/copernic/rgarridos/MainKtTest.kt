package cat.copernic.rgarridos

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.assertTimeout
import java.time.Duration

internal class MainKtTest {
    val p = Point(5.0, 2.0)
    val p1 = Point(7.2, 8.5)
    val p2 = Point(9.3, 10.4)
    val p0 = Point(8.25, 9.45)
    val list = listOf<Int>()

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            println("*** Inicio tests... ")
        }

        @AfterAll
        @JvmStatic
        fun end() {
            println("Final tests... ***")
        }
    }

    @BeforeEach
    fun setUp() {
        println("Inicio test ...")
    }

    @AfterEach
    fun tearDown() {
        println("Final test...")
    }

    @Test
    fun imc() {
        assertEquals(17.89, cat.copernic.rgarridos.imc(50.50, 1.68), 1e-2)
    }

    @Test
    fun secondDegreeEquation() {
        assertEquals(Pair(-4.0, -4.0), cat.copernic.rgarridos.secondDegreeEquation(1.0, 8.0, 16.0))
    }

    @Test
    fun distance() {
        assertEquals(9.43, cat.copernic.rgarridos.distance(p, p2), 1e-2)
    }

    @Test
    fun slope() {
        assertEquals(2.95, cat.copernic.rgarridos.slope(p, p1), 1e-2)
    }

    @Test
    fun midPoint() {
        assertEquals(p0, cat.copernic.rgarridos.midPoint(p1, p2))
    }

    @Test
    @DisplayName("Display Score!")
    fun `displayScore output`() {
        val output = tapSystemOut {
            cat.copernic.rgarridos.displayScore(4.86)
        }
        assertEquals("No supera", output.trim())
    }

    @Test
    @DisplayName("Mínimo y máximo en lista vacía")
    fun `findMinAndMax for exception`() {
        assertThrows<IllegalArgumentException> { cat.copernic.rgarridos.findMinAndMax(list) }
    }

    /*
    @ParameterizedTest
    @CsvSource
    @DisplayName
    @ValueSource
    @MethodSource
*/
    @Test
    @DisplayName("Closets Point")
    fun `closest for timeout`() {
        assertTimeout(
            Duration.ofMillis(30)) {
            cat.copernic.rgarridos.closest(p, p1, p2)
        }
        assertEquals(p1, cat.copernic.rgarridos.closest(p, p1, p2))
    }
}
