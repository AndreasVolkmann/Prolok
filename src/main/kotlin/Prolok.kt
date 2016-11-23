import org.jpl7.Atom
import org.jpl7.Query

fun consult(path: String) = Query("consult", Atom(path))
