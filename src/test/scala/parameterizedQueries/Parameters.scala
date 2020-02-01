package parameterizedQueries

import org.scalatest.funsuite.AnyFunSuite

class Parameters extends AnyFunSuite {

  test("parameters") {

    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    case class Country(code: String, name: String, pop: Int, gnp: Option[BigDecimal])
    val country: Decoder[Country] = (bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt).gmap[Country]

    val f =
      sql"""
      select code, name, population, gnp
      from country
      where population > $int4
      and   population < $int4
      """.query(country)

    val mySelect = Setup.session.use(_.prepare(f).use { ps =>
      ps.stream(150000000 ~ 200000000, 64).take(2).compile.to(List)
    })

    assert(
      mySelect.unsafeRunSync() == List(
        Country("BRA", "Brazil", 170115000, Some(776739.0)),
        Country("PAK", "Pakistan", 156483000, Some(61289.0))
      )
    )
  }
}
