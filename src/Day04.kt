fun main() {
    val input = readInput("Day04").split("\n")
    input
        .map { assignment -> assignment.split(",") }
        .map { assignment ->
            assignment.map { it.split("-") }
        }
        .map { assignments ->
            assignments
                .map { it.map(String::toInt) }
                .sortedBy { a -> a[0] }
        }
        .also { assignments ->
            assignments.sumOf { (a1, a2) ->
                val (start1, end1) = a1
                val (start2, end2) = a2

                if (
                    start1 >= start2 &&
                    end1 <= end2
                    || start2 >= start1 &&
                    end2 <= end1
                ) {
                    1L
                } else {
                    0L
                }
            }.also(::println)
        }
        .also { assignments ->
            assignments.sumOf { (a1, a2) ->
                val (_, end1) = a1
                val (start2, _) = a2

                if (start2 <= end1) {
                    1L
                } else {
                    0L
                }
            }.also(::println)
        }
}