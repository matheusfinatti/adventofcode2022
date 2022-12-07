// bvwbjplbgvbhsrlpgdmjqwftvncz = 5
fun main() {
    val input = readInput("Day06")
    input
        .also { println(it.findMarker(4)) }
        .also { println(it.findMarker(14)) }
}

fun String.findMarker(distinct: Int) =
    fold(0 to listOf<Char>()) { acc, c ->
        if (acc.second.distinct().count() == distinct) {
            acc.first to acc.second
        } else {
            acc.first + 1 to acc.second.dropIf(1) { count() == distinct } + listOf(c)
        }
    }.first