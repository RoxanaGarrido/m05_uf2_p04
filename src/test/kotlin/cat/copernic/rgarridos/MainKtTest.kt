package cat.copernic.rgarridos

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.assertTimeout

internal class MainKtTest {

    companion object{
        @BeforeAll
        @JvmStatic
        fun init(){
            println("*** Inicio tests... ")
        }

        @AfterAll
        @JvmStatic
        fun end(){
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
        assertEquals(17.89, cat.copernic.rgarridos.imc(50.50,1.68), 1e-2)
    }

    @Test
    fun secondDegreeEquation() {
        assertEquals(Pair(-4.0, -4.0), cat.copernic.rgarridos.secondDegreeEquation(1.0,8.0,16.0))
    }

    @Test
    fun distance() {
    }

    @Test
    fun slope() {
    }

    @Test
    fun midPoint() {
    }

    @Test
    fun displayScore() {
    }

    @Test
    fun findMinAndMax() {
    }
/*
    @ParameterizedTest
    @CsvSource
    @DisplayName
    @ValueSource
    @MethodSource
    //org.junit.jupiter.api.assertTimeout
    //com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
    //Test de control de excepciones


*/
    @Test
    private fun closest() {
    }
}