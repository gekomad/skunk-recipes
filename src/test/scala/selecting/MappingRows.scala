package selecting

import org.scalatest.funsuite.AnyFunSuite

class MappingRows extends AnyFunSuite {

  test("mapping rows to a case class") {
    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    case class Country(code: String, name: String, pop: Int, gnp: Option[BigDecimal])

    val country: Decoder[Country] = (bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt).gmap[Country]

    val query = sql"select code, name, population, gnp from country order by code limit 3".query(country)

    val mySelect = Setup.session.use(_.execute(query))

    assert(
      mySelect.unsafeRunSync == List(
        Country("ABW", "Aruba", 103000, Some(828.00)),
        Country("AFG", "Afghanistan", 22720000, Some(5976.00)),
        Country("AGO", "Angola", 12878000, Some(6648.00))
      )
    )
  }

}
