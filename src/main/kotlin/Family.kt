import org.jpl7.JPL
import org.jpl7.Query
import org.jpl7.Term
import org.jpl7.fli.Prolog

object Family {

    fun Any?.asTerm() = this as Term

    @JvmStatic
    fun main(args: Array<String>) {
        Query.hasSolution("use_module(library(jpl))") // only because we call e.g. jpl_pl_syntax/1 below
        val swi = Query.oneSolution("current_prolog_flag(version_data,Swi)")["Swi"] as Term
        println("swipl.version = " + swi.arg(1) + "." + swi.arg(2) + "." + swi.arg(3))
        println("swipl.syntax = " + Query.oneSolution("jpl_pl_syntax(Syntax)")["Syntax"])
        println("swipl.home = " + Query.oneSolution("current_prolog_flag(home,Home)")["Home"].asTerm().name())
        println("jpl.jar = " + JPL.version_string())
        println("jpl.dll = " + Prolog.get_c_lib_version())
        println("jpl.pl = " + Query.oneSolution("jpl_pl_lib_version(V)")["V"].asTerm().name())

        val consult = consult("src\\main\\resources\\family.pl")
        println("$consult " + if (consult.hasSolution()) "succeeded" else "failed")

        val t2 = "child_of(joe, ralf)"
        println(t2 + " is " + if (Query.hasSolution(t2)) "provable" else "not provable")

        val t3 = "descendent_of(steve, ralf)"
        println(t3 + " is " + if (Query.hasSolution(t3)) "provable" else "not provable")

        val t4 = "descendent_of(X, ralf)"
        println("first solution of " + t4 + ": X = " + Query.oneSolution(t4)["X"])
        val ss4 = Query.allSolutions(t4)
        println("all solutions of " + t4)
        for (i in ss4.indices) {
            println("X = " + ss4[i]["X"])
        }
        println("each solution of " + t4)
        val q4 = Query(t4)
        while (q4.hasMoreSolutions()) {
            val s4 = q4.nextSolution()
            println("X = " + s4["X"])
        }

        val t5 = "descendent_of(X,Y)"
        val q5 = Query(t5)
        println("each solution of " + t5)
        while (q5.hasMoreSolutions()) {
            val s5 = q5.nextSolution()
            println("X = " + s5["X"] + ", Y = " + s5["Y"])
        }
    }


}
