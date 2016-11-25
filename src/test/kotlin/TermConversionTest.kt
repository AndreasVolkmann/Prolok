import com.natpryce.hamkrest.should.shouldMatch
import org.amshove.kluent.AnyException
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.jpl7.Atom
import org.jpl7.Integer
import org.jpl7.Variable
import org.junit.Test

class TermConversionTest {


    @Test
    fun `A String of numbers should be converted to Integer`() {
        val numbers = "1005"
        val res = numbers.toTerm()
        res as Integer
        res shouldEqual Integer(numbers.toLong())
    }

    @Test
    fun `A String containing some non numerals should not be Integer`() {
        val numbers = "1005e"
        val res = numbers.toTerm()

        val func = { res as Integer}
        func shouldThrow ClassCastException::class

        res shouldEqual Atom(numbers)
    }

    @Test
    fun `Variable String not Integer`() {
        val text = "A8359359"
        val res = text.toTerm()

        val func = {res as Integer}
        func shouldThrow ClassCastException::class

        res shouldEqual Variable(text)
    }


}