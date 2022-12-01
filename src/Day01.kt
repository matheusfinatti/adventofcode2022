fun main() {
    val input = readInput("Day01").trim().split("\n\n")
    input.map { s -> s.split("\n").map(String::toInt) }
        .fold(emptyList<Int>()) { elves, elf -> elves + listOf(elf.sum()) }
        .also { elves -> println(elves.max()) } // part01
        .also { elves -> println(elves.sortedDescending().take(3).sum()) } // part02
}
