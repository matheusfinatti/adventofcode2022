import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


typealias Stack<T> = MutableList<T>

fun <T> Stack<T>.push(item: T) = add(count(), item)
fun <T> Stack<T>.pop(): T? = if(isNotEmpty()) removeAt(count() - 1) else null
fun <T> Stack<T>.peek(): T? = if(isNotEmpty()) this[count() - 1] else null
fun <T> Stack<T>.hasMore() = isNotEmpty()
fun <T> Stack<T>.dropLast(n: Int): Stack<T> = dropLast(n)
fun <T> stackOf(vararg args: T): Stack<T> = mutableListOf(*args)

fun <T> List<T>.dropIf(n: Int, predicate: List<T>.() -> Boolean) =
    if (predicate()) drop(n) else this
inline fun <reified T> Array<Array<T>>.map2dIndexed(function: (Int, Int, T) -> T): Array<Array<T?>> {
    val data = Array(this.size) { arrayOfNulls<T>(this[0].size) }

    for (i in indices) {
        for (j in data[i].indices) {
            data[i][j] = function(i, j, this[i][j])
        }
    }
    return data
}
