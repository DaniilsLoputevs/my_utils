package skills.abilities

import java.io.File

//class Blink : Ability {
//}

fun main() {
//    val rsl ="rp".repeat(100)
//    println("rsl = $rsl")

    val path = "C:/Users/Admin/Desktop/stub_size.txt"
//    val content = "grb_p".repeat(2_000_00) // 1 000 000 байт
//    val content = "grb_p".repeat(8_000_00) // 4 000 000 байт
    val content = "abcd_abcd_".repeat(19_000_00)
    File(path)
//        .writeText(content)
//        .appendText(content)
    println("finish! ^_^")

    val l = MutableList(50) {"index" }
    println(l)
}