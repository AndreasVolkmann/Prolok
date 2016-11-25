import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.jpl7.Atom
import org.jpl7.Float
import org.jpl7.Integer
import org.jpl7.Variable
import org.junit.Test

class TermConversionTest {


    @Test
    fun `An Int should be converted to Integer`() {
        val numbers = 1005
        val res = numbers.toTerm()
        res as Integer
        res shouldEqual Integer(numbers.toLong())
    }

    @Test
    fun `A String containing some non numerals should not be Integer`() {
        val numbers = "1005e"
        val res = numbers.toTerm()

        val func = { res as Integer }
        func shouldThrow ClassCastException::class

        res shouldEqual Atom(numbers)
    }

    @Test
    fun `Variable String not Integer`() {
        val text = "A8359359"
        val res = text.toTerm()

        val func = { res as Integer }
        func shouldThrow ClassCastException::class

        res shouldEqual Variable(text)
    }

    @Test
    fun `Multiple arguments of any type should be parsed as Terms`() {
        val res = termOf(1, "atom", 1.5, "Variable")

        res[0] is Integer
        res[1] is Atom
        res[2] is Float
        res[3] is Variable

        res[0] shouldEqual Integer(1)
        res[1] shouldEqual Atom("atom")
        res[2] shouldEqual org.jpl7.Float(1.5)
        res[3] shouldEqual Variable("Variable")
    }


}