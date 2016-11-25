import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.jpl7.Atom
import org.jpl7.JPL
import org.jpl7.Query
import org.jpl7.fli.Prolog
import org.junit.jupiter.api.Test

class ProlokTest {

    val X = "X"
    val Y = "Y"

    fun terms(vararg items: String) = items.map(String::toTerm)

    @Test
    fun setup() {
        Query.hasSolution("use_module(library(jpl))")

        val swi = query("current_prolog_flag", "version_data", "Swi").oneSolution()["Swi"]!!

        println("swipl.version = " + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3))
        println("swipl.syntax = " + Query.oneSolution("jpl_pl_syntax(Syntax)")["Syntax"])
        println("swipl.home = " + Query.oneSolution("current_prolog_flag(home,Home)")["Home"]!!.name())
        println("jpl.jar = " + JPL.version_string())
        println("jpl.dll = " + Prolog.get_c_lib_version())
        println("jpl.pl = " + Query.oneSolution("jpl_pl_lib_version(V)")["V"]!!.name())
    }

    @Test
    fun variables() {
        consult("family.pl") {
            val query = query("descendent_of", X, "ralf")
            query.toString() shouldEqual "descendent_of( X, ralf )"

            println("first solution of " + query + ": X = " + query.oneSolution()[X])

            println("all solutions of $query")
            val solutions = query.getSolutions(X)
            solutions.forEach { println("X = $it") }
            solutions shouldContain Atom("joe")
            solutions shouldContain Atom("mary")
            solutions shouldContain Atom("steve")


            val t5 = query("descendent_of", X, Y)
            t5.goal().arity() shouldEqual 2
            println("each solution of " + t5)

            val sols = t5.getSolutions(X, Y)
            sols.forEach(::println)
            sols shouldContain terms("joe", "ralf")
            sols shouldContain terms("mary", "ralf")
            sols shouldContain terms("steve", "ralf")
            sols shouldContain terms("mary", "joe")
            sols shouldContain terms("steve", "joe")
        }
    }


}