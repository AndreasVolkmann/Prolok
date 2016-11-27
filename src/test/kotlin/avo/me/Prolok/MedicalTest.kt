package avo.me.Prolok

import avo.me.Prolok.consult
import avo.me.Prolok.getSolutions
import avo.me.Prolok.query
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

class MedicalTest {

    @Test
    fun medical() {
        fun doctor(x: Any) = query("doctor", x)
        fun nurse(x: Any) = query("nurse", x)
        fun medical(x: Any) = query("medical", x)
        fun multipleJobs(x: Any) = query("multipleJobs", x)

        consult("medical.pl") {

            doctor(1) shouldBe true
            doctor(2) shouldBe true
            doctor(3) shouldBe true
            doctor(4) shouldBe true
            doctor("1") shouldBe false
            doctor("2") shouldBe false
            doctor("3") shouldBe false
            doctor("4") shouldBe false
            doctor(1.0) shouldBe false

            nurse(5) shouldBe true

            medical(1) shouldBe true
            medical(8) shouldBe true

            multipleJobs("X") shouldBe false
            multipleJobs("X").getSolutions("X").isEmpty() `should be`  true
            multipleJobs("X").getSolutions().isEmpty() `should be`  true

            doctor("50") shouldBe true // still fails
        }



    }


}