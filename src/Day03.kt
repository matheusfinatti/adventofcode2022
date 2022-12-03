fun main() {
    val input = readInput("Day03").split("\n")
    input.map { items -> items.chunked(items.count() / 2) }
        .map { sacks ->
            val hash = mutableSetOf<Char>()
            sacks[0].forEach { c ->
                hash.add(c)
            }
            sacks[1].forEach { c ->
                if (hash.contains(c)) return@map c
            }
            sacks[0][0]
        }
        .sumOf(::priority)
        .also(::println)

    input.chunked(3)
        .also(::println)
        .map { group ->
            group.map { str -> str.toCharArray().distinct() }
        }
        .also(::println)
        .map { groups ->
            val hash = mutableMapOf<Char, Int>()
            for (group in groups) {
                for (char in group) {
                    hash[char] = hash.getOrElse(char) { 0 } + 1
                }
            }
            hash.forEach { (c, i) ->
                if (i == 3) {
                    return@map c
                }
            }
            groups[0][0]
        }
        .sumOf(::priority)
}

fun priority(c: Char) =
    if (c.isUpperCase()) {
        c - 'A' + 26
    } else {
        c - 'a'
    } + 1