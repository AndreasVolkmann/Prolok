import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class MedicalTest {

    @Test
    fun medical() {
        fun doctor(x: Any) = query("doctor", x).hasSolution()
        fun nurse(x: Any) = query("nurse", x).hasSolution()
        fun medical(x: Any) = query("medical", x).hasSolution()
        fun multipleJobs(x: Any) = query("multipleJobs", x).hasSolution()

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
            query("multipleJobs", "X").getSolutions("X").isEmpty() shouldBe true

            doctor("50") shouldBe true // still fails
        }



    }


}