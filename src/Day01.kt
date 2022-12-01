import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.max

fun main() {
    val input = readInput("Day01").trim().split("\n\n")

    val elves = groupElves(input).sumElvesSnacks()
    println(part01(elves))
    println(part02(elves))
}

fun groupElves(input: List<String>) =
    input.map { s ->
        s.split("\n").map(String::toInt)
    }

fun List<List<Int>>.sumElvesSnacks() =
    fold(listOf<Int>()) { elves, elf ->
        elves + listOf(elf.sum())
    }

fun part01(elves: List<Int>) = elves.max()

fun part02(elves: List<Int>) =
    elves.sortedDescending().take(3).sum()
