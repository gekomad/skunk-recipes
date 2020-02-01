package selecting
import cats.effect.IO
import org.scalatest.funsuite.AnyFunSuite

class Join extends AnyFunSuite {

  // issue https://github.com/tpolecat/skunk/issues/129
  ignore("join") {

    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    case class Country(name: String, code: String)
    case class City(name: String, district: String)

    val join: IO[List[(Country, Option[City])]] = Setup.session.use { s =>
      val q: Query[Void, (Country, Option[City])] = sql"""
                 select c.name, c.code, k.name, k.district
                 from country c
                 left outer join city k
                 on c.capital = k.id
                 order by c.code desc"""
        .query(varchar ~ bpchar(3) ~ varchar.opt ~ varchar.opt)
        .map { case a ~ b ~ Some(c) ~ Some(d) => (Country(a, b), Some(City(c, d))) }
      s.execute(q)
    }
    val a = join.unsafeRunSync
    val o = a.filter(_._2.isEmpty)
    assert(a.length == 239)
    assert(o.length == 7)
    assert(
      a.take(2) == List(
        (Country("Zimbawe", "ZWE"), Some(City("Harare", "Harare"))),
        (Country("Zambia", "ZMB"), Some(City("Lusaka", "Lusaka")))
      )
    )

  }
}
