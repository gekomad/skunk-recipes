package selecting

import org.scalatest.funsuite.AnyFunSuite
import cats.effect.unsafe.implicits.global

class SelectMultipleColumns extends AnyFunSuite {

  import skunk.implicits._
  import skunk.codec.all._
  import skunkrecipes.Setup

  test("select multiple columns") {
    val query =
      sql"select code, name, population, gnp from country order by code limit 3".query(
        bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt
      )

    val mySelect = Setup.session.use(_.execute(query))

    assert(
      mySelect.unsafeRunSync() == List(
        ((("ABW", "Aruba"), 103000), Some(828.00)),
        ((("AFG", "Afghanistan"), 22720000), Some(5976.00)),
        ((("AGO", "Angola"), 12878000), Some(6648.00))
      )
    )
  }
}
