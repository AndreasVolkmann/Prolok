import org.jpl7.*
import java.io.File

object Prolok

fun consult(path: String) = (if (File(path).exists()) query("consult", path)
else query("consult", Prolok::class.java.getResource(path).path)).hasSolution()


fun termOf(vararg items: String): Array<Term> = items.map {
    if (it.first().isUpperCase() or it.startsWith('_')) Variable(it)
    else Atom(it)
}.toTypedArray()


fun query(command: String, vararg terms: String) = Query(command, termOf(*terms))

fun compound(command: String, vararg terms: String) = Compound(command, termOf(*terms))


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


//infix fun String.query(vararg arguments: String) = ""