package dialogs

import kotlin.random.Random

class RandomChance {
//    var amplititudes  = IntArray(10) { Random.nextInt() }.asList()
}

fun calcChance()  = 100 - (BooleanArray(100) { Random.nextBoolean() }.asList().filter { it }.count())
fun main() {
    val dropRate = 45
    val dropProc = calcChance()
    val drop = (dropProc +dropRate) >= 101

    println("dropRate: $dropRate")
    println("dropProc: $dropProc")
    println("is item dropped: $drop")
}
