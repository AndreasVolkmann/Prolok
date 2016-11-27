package avo.me.Prolok

import org.junit.Test

class PrologToFileTest {

    fun doctor(id: Int) = query("doctor", id)

    @Test
    fun toFile() {

        val file = "prolog/test.pl"

        Prolok.toFile(file, listOf(
                doctor(1),
                doctor(3),
                doctor(6), // helper function
                query("complex", 2, 999, "complicated"), // using query
                "isPerson(X):- doctor(X); complex(2, 999, X)." // Raw String
        ))


        consult(file) {
            doctor(1) shouldBe true
            doctor(3) shouldBe true
            doctor(6) shouldBe true

            query("complex", 2, 999, "complicated") shouldBe true
            query("complex", 2, 999, "wrong") shouldBe false
            query("isPerson", "complicated") shouldBe true

            query("isPerson", "4") shouldBe false
            doctor(2) shouldBe false
        }

    }


}