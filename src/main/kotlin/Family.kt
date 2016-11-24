import org.jpl7.JPL
import org.jpl7.Query
import org.jpl7.Term
import org.jpl7.fli.Prolog

object Family {

    fun Any?.asTerm() = this as Term

    fun descendantOf(one: String, two: String) = query("descendent_of", one, two)

    @JvmStatic
    fun main(args: Array<String>) {
        setUp()
        consult("family.pl")

        val t2 = "child_of(joe, ralf)"
        println(t2 + " is " + if (Query.hasSolution(t2)) "provable" else "not provable")

        val t3 = descendantOf("steve", "ralf")
        println("$t3 is " + if (t3.hasSolution()) "provable" else "not provable")

        val t4 = descendantOf("X", "ralf")
        println("first solution of " + t4 + ": X = " + t4.oneSolution()["X"])
        val ss4 = t4.allSolutions()
        println("all solutions of $t4")
        ss4.indices.forEach { i -> println("X = " + ss4[i]["X"]) }

        println("each solution of " + t4)
        val q4 = t4
        while (q4.hasMoreSolutions()) {
            val s4 = q4.nextSolution()
            println("X = " + s4["X"])
        }

        val t5 = descendantOf("X", "Y")
        println("each solution of " + t5)
        while (t5.hasMoreSolutions()) {
            val s5 = t5.nextSolution()
            println("X = " + s5["X"] + ", Y = " + s5["Y"])
        }

        test()
    }


    fun setUp() {
        Query.hasSolution("use_module(library(jpl))")
        val swi = Query.oneSolution("current_prolog_flag(version_data,Swi)")["Swi"] as Term
        println("swipl.version = " + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3))
        println("swipl.syntax = " + Query.oneSolution("jpl_pl_syntax(Syntax)")["Syntax"])
        println("swipl.home = " + Query.oneSolution("current_prolog_flag(home,Home)")["Home"].asTerm().name())
        println("jpl.jar = " + JPL.version_string())
        println("jpl.dll = " + Prolog.get_c_lib_version())
        println("jpl.pl = " + Query.oneSolution("jpl_pl_lib_version(V)")["V"].asTerm().name())
    }

    fun test() {


        val teacher_of = compound("teacher_of", "aristotle", "alexander")


    }


}
