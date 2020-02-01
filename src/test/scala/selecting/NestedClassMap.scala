package selecting

import org.scalatest.funsuite.AnyFunSuite

class NestedClassMap extends AnyFunSuite {

  test("nested case class Map") {
    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    case class Code(code: String)
    case class Country(name: String, pop: Int, gnp: Option[BigDecimal])

    val query =
      sql"select code, name, population, gnp from country where code='ABW'"
        .query(bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt)
        .map { case a ~ b ~ c ~ d => (Code(a), Country(b, c, d)) }

    val mySelect = Setup.session.use(_.execute(query).map(_.toMap))

    assert(mySelect.unsafeRunSync == Map(Code("ABW") -> Country("Aruba", 103000, Some(828.00))))
  }

}
