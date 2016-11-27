package avo.me.Prolok

import org.amshove.kluent.shouldBe
import org.jpl7.Query

infix fun Query.shouldBe(bool: Boolean) = this.hasSolution() shouldBe bool