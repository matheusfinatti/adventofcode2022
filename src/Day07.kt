data class File(
    val name: String,
    val size: Long = 0,
    val parent: File? = null,
    val children: MutableList<File> = mutableListOf()
)

val root = File("/")
fun main() {
    val input = readInput("Day07").split("\n")

    val lsAcc = mutableListOf<String>()

    var node = root

    for (output in input) {
        if (output.startsWith("$")) {
            val c = output.split(" ")
            if (c.count() == 3) { // cd
                node.ls(lsAcc)
                lsAcc.clear()
                node = node.cd(c[2])!!
            }
        } else {
            lsAcc += output
        }
    }

    if (lsAcc.isNotEmpty()) {
        node.ls(lsAcc)
    }
    //root.lsTree()
    val nr = root.fillDirSizes()

    println(nr.size(limit = 100_000))

    val unused = 70_000_000 - nr.size
    val req = 30_000_000 - unused
    val files = nr.findFiles(req)
//    println(files)
    files.minOfOrNull { it.size }.also(::println)
}
fun File.cd(dst: String) =
    when (dst) {
        ".." -> parent
        "/" -> root
        else -> children.firstOrNull { file ->
            file.name == dst
        } ?: error("Dir doesn't exist")
    }
fun File.ls(files: List<String>) {
    for (file in files) {
        if (file.startsWith("dir")) {
            val (_, name) = file.split(" ")
            children.add(File(name, parent = this))
        } else {
            val (size, name) = file.split(" ")
            children.add(File(name, size.toLong(), parent = this))
        }
    }
}
fun File.lsTree(depth: Int = 0) {
    repeat(depth) { print("\t") }
    print(" - $name ")
    if (children.isEmpty()) {
        print("(file, size=$size)")
        println()
    } else {
        print("(dir, size=$size)")
        println()
        children.forEach { file ->
            file.lsTree(depth + 1)
        }
    }
}

fun File.size(limit: Long = Long.MAX_VALUE): Long =
    if (children.isEmpty()) {
        0L
    } else {
        children.fold(
            size.takeIf { it <= limit } ?: 0L
        ) { acc, f ->
            acc + f.size(limit)
        }
    }

fun File.fillDirSizes(): File {
    if (children.isEmpty()) return this

    val children = children.map(File::fillDirSizes).toMutableList()
    return copy(
        size = children.sumOf { it.size },
        children = children,
    )
}

fun File.findFiles(req: Long): List<File> =
    if (children.isEmpty()) {
        emptyList()
    } else {
        if (size >= req) {
            listOf(this) + children.flatMap { it.findFiles(req) }
        } else {
            emptyList()
        }
    }