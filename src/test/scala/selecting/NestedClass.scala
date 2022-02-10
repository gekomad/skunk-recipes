package selecting

import org.scalatest.funsuite.AnyFunSuite
import cats.effect.unsafe.implicits.global

class NestedClass extends AnyFunSuite {

  test("nested case class") {
    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    case class Code(code: String)
    case class Country(name: String, pop: Int, gnp: Option[BigDecimal])

    val query =
      sql"""select code, name, population, gnp from country order by code limit 3"""
        .query(bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt)
        .map { case a ~ b ~ c ~ d => (Code(a), Country(b, c, d)) }

    val mySelect = Setup.session.use(_.execute(query))

    assert(
      mySelect.unsafeRunSync() == List(
        (Code("ABW"), Country("Aruba", 103000, Some(828.00))),
        (Code("AFG"), Country("Afghanistan", 22720000, Some(5976.00))),
        (Code("AGO"), Country("Angola", 12878000, Some(6648.00)))
      )
    )
  }

}
