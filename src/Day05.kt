val stacks = listOf(
    stackOf('W', 'R', 'F'),
    stackOf('T', 'H', 'M', 'C', 'D', 'V', 'W', 'P'),
    stackOf('P', 'M', 'Z', 'N', 'L'),
    stackOf('J', 'C', 'H', 'R'),
    stackOf('C', 'P', 'G', 'H', 'Q', 'T', 'B'),
    stackOf('G', 'C', 'W', 'L', 'F', 'Z'),
    stackOf('W', 'V', 'L', 'Q', 'Z', 'J', 'G', 'C'),
    stackOf('P', 'N', 'R', 'F', 'W', 'T', 'V', 'C'),
    stackOf('J', 'W', 'H', 'G', 'R', 'S', 'V'),
)
fun main() {
    val input = readInput("Day05").split("\n\n")[1]
    input
        .split(" ", "\n")
        .mapNotNull { s ->
            if (s.any(Char::isDigit)) {
                s.toInt()
            } else {
                null
            }
        }
        .chunked(3)
//        .also { moves ->
//            moves.forEach { (amount, src, dst) ->
//                repeat(amount) {
//                    val crate = stacks[src - 1].pop()
//                    stacks[dst - 1].push(crate!!)
//                }
//            }
//            stacks.joinToString(separator = "") { stack ->
//                stack.peek().toString()
//            }.also(::println)
//        }
        .also { moves ->
            moves.forEach { (amount, src, dst) ->
                val moved = stacks[src - 1]
                    .subList(
                        stacks[src - 1].count() - amount,
                        stacks[src - 1].count()
                    )

                for (i in 0 until amount) {
                    stacks[dst - 1].push(moved[i])
                }
                for (i in 0 until amount) {
                    stacks[src - 1].pop()
                }
            }

            stacks.joinToString(separator = "") { stack ->
                stack.peek().toString()
            }.also(::println)
        }
}
