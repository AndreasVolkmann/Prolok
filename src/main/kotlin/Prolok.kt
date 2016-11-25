import org.jpl7.*
import org.jpl7.Float
import java.io.File

object Prolok

/**
 * Consulting
 */
fun getFile(path: String) = if (File(path).exists()) query("consult", path)
else query("consult", Prolok::class.java.getResource(path).path)

inline fun consult(path: String, logic: (Query) -> Unit) {
    val file = getFile(path)
    try {
        if (!file.hasSolution()) throw Exception("File could not be consulted: $path")
        logic(file)
    } finally {
        file.close()
    }
}

/**
 * Terms and Queries
 */
fun <T> termOf(vararg items: T): Array<Term> = items.map { it.toTerm() }.toTypedArray()

fun String.toTerm() = if (this.first().isUpperCase() or this.startsWith('_')) Variable(this)
else Atom(this)

fun <T> T.toTerm() = when (this) {
    is String -> this.toTerm()
    is Int -> Integer(this.toLong())
    is Double -> Float(this)
    else -> Atom(this.toString())
}


fun query(command: String, vararg terms: Any) = Query(command, termOf(*terms))


/**
 * For multiple items return List of List of Terms
 */
fun Query.getSolutions(vararg items: String): List<List<Term?>> {
    val ss = this.allSolutions()
    return ss.map { solution -> items.map { solution[it] } }
}

/**
 * For one item return List of Terms
 */
fun Query.getSolutions(item: String): List<Term?> {
    val ss = this.allSolutions()
    return ss.map { it[item] }
}

fun Query.getSolutions(): List<List<Term>> = this.allSolutions().map { it.values.toList() }
