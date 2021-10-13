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
    @DisplayName("Calculo imc")
    fun imc() {
        assertEquals(17.89, cat.copernic.rgarridos.imc(50.50, 1.68), 1e-2)
    }

    @ParameterizedTest
    @CsvSource("85.2,1.74,28.1", "45.6,1.56,18.7", "200.50,1.89,56.1")
    fun `imc parameterized`(a: Double, b: Double, c: Double) {
        assertEquals(c, cat.copernic.rgarridos.imc(a, b),1e-1)
    }
    @Test
    @DisplayName("Ecuación de segundo grado")
    fun secondDegreeEquation() {
        assertEquals(Pair(-4.0, -4.0), cat.copernic.rgarridos.secondDegreeEquation(1.0, 8.0, 16.0))
    }

    @Test
    @DisplayName("Distancia entre dos points")
    fun distance() {
        assertEquals(9.43, cat.copernic.rgarridos.distance(p, p2), 1e-2)
    }

    @Test
    @DisplayName("Pendiente de una recta")
    fun slope() {
        assertEquals(2.95, cat.copernic.rgarridos.slope(p, p1), 1e-2)
    }

    @Test
    @DisplayName("Punto medio")
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
    @DisplayName("Scores")
    @ParameterizedTest
    @ValueSource(doubles = [7.0, 7.5, 8.9])
    fun `displayScore for valueSource`(n: Double) {
        val output = tapSystemOut {
            cat.copernic.rgarridos.displayScore(n)
        }
        assertEquals("Notable", output.trim())
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
