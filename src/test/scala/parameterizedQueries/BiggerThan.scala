package parameterizedQueries

import org.scalatest.funsuite.AnyFunSuite
import cats.effect.unsafe.implicits.global

class BiggerThan extends AnyFunSuite {

  import skunk._
  import skunk.implicits._
  import skunk.codec.all._
  import skunkrecipes.Setup

  test("bigger than") {

    case class Country(code: String, name: String, pop: Int, gnp: Option[BigDecimal])

    val country: Decoder[Country] = (bpchar(3) *: varchar *: int4 *: numeric(10, 2).opt).to[Country]
    val minPop                    = 150000000
    val f =
      sql"""select code, name, population, gnp from country where population > $int4 order by code""".query(country)

    val mySelect = Setup.session.use(_.prepare(f).flatMap { ps =>
      ps.stream(minPop, 64).take(2).compile.to(List)
    })

    assert(
      mySelect.unsafeRunSync() == List(
        Country("BRA", "Brazil", 170115000, Some(776739.00)),
        Country("CHN", "China", 1277558000, Some(982268.00))
      )
    )
  }
}
