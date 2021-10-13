package cat.copernic.rgarridos


import kotlin.math.floor
import kotlin.math.*

/**
 * Implementació i tests de tota mena
 */

data class Point(val x: Double, val y: Double)

/*
* Índice de masa corporal
*
* imc = weight / height^2
* */
fun imc(weight: Double, height: Double): Double = weight / height.pow(2)

/*
* https://en.wikipedia.org/wiki/Quadratic_equation
*
* (-b ± sqrt(b^2 - 4ac)) / 2a
* */
fun secondDegreeEquation(a: Double, b: Double, c: Double): Pair<Double, Double> {
    var valor1: Double = (-b + sqrt(b.pow(2.0) - (4 * a * c) )) / (2 * a)
    var valor2: Double = (-b - sqrt( b.pow(2.0) - (4 * a * c) )) / (2 * a)
    return Pair(valor1, valor2)
}

/*
* Cálculo de la distancia entre dos puntos
*
* distance = √[(x2 – x1)^2 +(y2 – y1)^2]
* */
fun distance(p1: Point, p2: Point): Double {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2.0) + Math.pow((p2.y - p1.y), 2.0))
}

/*
* Cáculo de la pendiente de una recta
*
* slope = (y2 – y1) / (x2 – x1)
* */
fun slope(p1: Point, p2: Point): Double {

   return (p2.y - p1.y) / (p2.x - p1.x)
}

/*
* Cálculo del punto medio de una recta
*
* midpoint = ((x1+x2)/2, (y1+y2)/2)
* */
fun midPoint(p1: Point, p2: Point): Point {
    val x: Double = ((p1.x + p2.x) / 2)
    val y: Double = ((p1.y + p2.y) / 2)
    return Point(x, y)
}

fun displayScore(score: Double) {
    val roundedScore = floor(score * 100) / 100
    when (roundedScore) {
        in 0.0..4.99 -> println("No supera")
        in 5.0..5.99 -> println("Supera")
        in 6.0..6.99 -> println("Bien")
        in 7.0..8.99 -> println("Notable")
        in 9.0..10.0 -> println("Excel·lent")
        else -> println("Nota invalida")
    }
}

/*
* Encuentra el menor y mayor
*
* [2,3,1,4,7,6,5] = (1,7)
* [] = IllegalArgumentException
* */
fun findMinAndMax(list: List<Int>): Pair<Int, Int> {
    val menor = 0
    val mayor = 0
    if(list.isEmpty()) throw IllegalArgumentException ("Void List!")
    else {
        val listOrdered = list.sorted()
        val listOrderedDes = list.sortedDescending()
        val menor = listOrdered[0]
        val mayor = listOrderedDes[0]
    }
    return Pair(menor, mayor)
}

/*
*
* Cálculo del punto más cercano a point. points es una lista de tipo Point
*
* */
fun closest(point: Point, vararg points: Point): Point {
    var menorD = Double.MAX_VALUE
    var temporal = 0.0
    var p = Point(0.0,0.0)
    points.forEach {
        temporal = distance(point, it)
        if(temporal < menorD) {menorD = temporal; p = it}
    }
    return p
}

fun main(){
    val p = Point(5.0,2.0)
    val p1 = Point(7.2,8.5)
    val p2 = Point(9.3,10.4)
/*
    var distancia = distance(p,p1)
    println(distancia)
    distancia = distance(p,p2)
    println(distancia)

    val pclose = closest(p, p1, p2)
    println(pclose)



    println(imc(50.50,1.68))


    println(secondDegreeEquation(1.0,8.0,16.0))


    println(distance(p,p2))
    println(slope(p, p1))
    println(midPoint(p1, p2))

 */
    val roundedScore = floor(4.868 * 100) / 100
    println(roundedScore)
    println(displayScore(4.868))
}