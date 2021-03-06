package cat.copernic.rgarridos

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.assertTimeout
import org.junit.jupiter.params.provider.Arguments
import java.time.Duration
import java.util.stream.Stream

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

        @JvmStatic
        fun provideParametersForMinMax(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(listOf(8, 12, 5, 4), Pair(4, 12)),
                Arguments.of(listOf(20, 89, 35, 2), Pair(2, 89)),
                Arguments.of(listOf(98,12,3,4,40), Pair(3, 98)),
            )
        }

        @JvmStatic
        fun provideParametersSlope(): Stream<Arguments?>? {
            val a = Point(5.0,8.2)
            val b = Point(2.4,7.2)
            val c = Point(4.3, 5.6)
            return Stream.of(
                Arguments.of(a, b, 0.38),
                Arguments.of(a, c, 3.71),
                Arguments.of(b, c, -0.84),
            )
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
    @DisplayName("Ecuaci??n de segundo grado")
    fun secondDegreeEquation() {
        assertEquals(Pair(-4.0, -4.0), cat.copernic.rgarridos.secondDegreeEquation(1.0, 8.0, 16.0))
    }

    @Test
    fun `ecuaci??n with timeout`(){
       val result = assertTimeout(
            Duration.ofMillis(8)) {
            cat.copernic.rgarridos.secondDegreeEquation(3.0,-30.0,-12.0)
        }
        assertEquals(Pair(10.385164807134503, -0.38516480713450346), result)
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

    @ParameterizedTest
    @MethodSource("provideParametersSlope")
    fun pendiente(p1: Point, p2: Point, d: Double){
        assertEquals(d, cat.copernic.rgarridos.slope(p1, p2), 1e-2)
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

    @ParameterizedTest
    @ValueSource(doubles = [7.0, 7.5, 8.9])
    fun `displayScore for valueSource`(n: Double) {
        val output = tapSystemOut {
            cat.copernic.rgarridos.displayScore(n)
        }
        assertEquals("Notable", output.trim())
    }

    @Test
    @DisplayName("M??nimo y m??ximo en lista vac??a")
    fun `findMinAndMax for exception`() {
        assertThrows<IllegalArgumentException> { cat.copernic.rgarridos.findMinAndMax(list) }
    }

    @ParameterizedTest
    @MethodSource("provideParametersForMinMax")
    fun `findMinAndMax with methodSource`(list: List<Int>, p: Pair<Int, Int>){
        assertEquals(p, cat.copernic.rgarridos.findMinAndMax(list))
    }

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

