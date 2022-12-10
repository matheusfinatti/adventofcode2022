fun main() {
    val input = readInput("Day08").replace("\r", "").split("\n")
    input
        .map(String::toList)
        .map { row -> row.map(Char::digitToInt).toTypedArray() }.toTypedArray()
        .also { matrix ->
            matrix.map2dIndexed { i, j, _ ->
                if (i == 0 || j == 0 || i == matrix.count() - 1 || j == matrix[i].count() - 1) {
                    1
                } else {
                    matrix.isVisible(i, j)
                }
            }.sumOf { row ->
                row.sumOf { it ?: 0 }
            }.also(::println)
        }.also { matrix ->
            matrix.map2dIndexed { i, j, _ ->
                matrix.scenicScore(i, j)
            }.maxOf { row ->
                row.maxOf { it ?: 0 }
            }.also(::println)
        }

}

fun Array<Array<Int>>.isVisible(x: Int, y: Int): Int {
    var left = 1
    val h = this[y][x]

    for (i in x - 1 downTo 0) {
        if (this[y][i] >= h) left = 0
    }

    var right = 1
    for (i in x + 1 until this[y].count()) {
        if (this[y][i] >= h) right = 0
    }

    var up = 1
    for (i in y - 1 downTo 0) {
        if (this[i][x] >= h) up = 0
    }

    var down = 1
    for (i in y + 1 until count()) {
        if (this[i][x] >= h) down = 0
    }

    return up or down or left or right
}

fun Array<Array<Int>>.scenicScore(x: Int, y: Int): Int {
    val h = this[y][x]

    var left = 0
    for (i in x - 1 downTo 0) {
        left++
        if (this[y][i] >= h) break
    }

    var right = 0
    for (i in x + 1 until this[y].count()) {
        right++
        if (this[y][i] >= h) break
    }

    var up = 0
    for (i in y - 1 downTo 0) {
        up++
        if (this[i][x] >= h) break
    }

    var down = 0
    for (i in y + 1 until count()) {
        down++
        if (this[i][x] >= h) break
    }

    return up * down * left * right
}