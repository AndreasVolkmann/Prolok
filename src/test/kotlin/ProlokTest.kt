import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldEqual
import org.jpl7.Atom
import org.jpl7.Query
import org.jpl7.Term
import org.junit.jupiter.api.Test

class ProlokTest {

    val X = "X"
    val Y = "Y"

    fun terms(vararg items: String) = items.map(::Atom)

    @Test
    fun variables() {
        consult("family.pl")

        val t4 = query("descendent_of", X, "ralf")
        t4.toString() shouldEqual "descendent_of( X, ralf )"

        println("first solution of " + t4 + ": X = " + t4.oneSolution()[X])

        println("all solutions of $t4")
        val solutions = t4.getSolutions(X)
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