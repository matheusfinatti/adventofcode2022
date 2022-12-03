import kotlin.math.abs

fun main() {
    val input = readInput("Day02").trim().split("\n")

    input
        .map { round -> round.split(" ").map(String::single) }
        .map { round -> listOf(round[0] - 'A', round[1] - 'X') }
        .also { game ->
            game.sumOf { round ->
                val (p1, p2) = round
                listOf(
                    when {
                        (p1 + 1) % 3 == p2 -> 6
                        p1 == p2 -> 3
                        else -> 0
                    },
                    p2 + 1,
                ).sum()
            }.also(::println)
        }
        .also { game ->
            game.sumOf { round ->
                val (p1, p2) = round
                // 1, 2, 3
                // p1=1, p2=1 -> 3
                // p1=1, p2=2 -> 1
                // p1=1, p2=3 -> 2
                // --
                // p1=2, p2=1 -> 1
                // p1=2, p2=2 -> 2
                // p1=2, p2=3 -> 3
                // --
                // p1=3, p2=1 -> 2
                // p1=3, p2=2 -> 3
                // p1=3, p2=3 -> 1
                listOf(
                    when (p1) {
                        0 -> when (p2) {
                            0 -> 3
                            1 -> 1
                            else -> 2
                        }
                        1 -> when (p2) {
                            0 -> 1
                            1 -> 2
                            else -> 3
                        }
                        else -> when (p2) {
                            0 -> 2
                            1 -> 3
                            else -> 1
                        }
                    },
                    p2 * 3,
                ).sum()
            }.also(::println)
        }


}