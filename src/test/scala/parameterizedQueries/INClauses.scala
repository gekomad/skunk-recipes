package parameterizedQueries

import org.scalatest.funsuite.AnyFunSuite

class INClauses extends AnyFunSuite {

  test("d") {

    import cats.effect._
    import cats.implicits._
    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import natchez.Trace.Implicits.noop

    import skunkrecipes.Setup

    case class Country(code: String, name: String, pop: Int, gnp: Option[BigDecimal])
    val country: Codec[Country] = (bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt).gimap[Country]

//    val data: Codec[Data] =  (int4 ~ bpchar ~ bool).gimap[Data]

    val examples: List[String] = List("USA", "BRA", "PAK", "GBR")
//    List(Data(10, "foo", true), Data(11, "bar", true), Data(12, "baz", false))

    val q = sql"VALUES ${country.values}".query(country)
//    Setup.session
//      .use { s =>
//        s.prepare(q).use { pq =>
//          for {
//            _  <- IO(println(q.sql))
//            ds <- pq.stream(examples, 64).compile.to(List)
//            _  <- ds.traverse(d => IO(println(d)))
//            _  <- IO(println(s"Are they the same? ${ds == examples}"))
//          } yield ExitCode.Success
//        }
//      }
//      .unsafeRunSync()

    println(1)
  }

//  test("IN Clauses") {
//
//    import cats.data.NonEmptyList
//    import skunk._
//    import skunk.implicits._
//    import skunk.codec.all._
//    import skunkrecipes.Setup
//
//    case class Country(code: String, name: String, pop: Int, gnp: Option[BigDecimal])
//    val country: Decoder[Country] = (bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt).gmap[Country]
//
//    def populationIn(range: Range, codes: NonEmptyList[String]) = {
//      val q =
//        sql"""
//        select code, name, population, gnp
//        from country
//        where population > ${range.min}
//        and   population < ${range.max}
//        and   VALUES $codes""".query(country)
//    }
//
//    val mySelect = Setup.session.use(_.execute(q))
//
//    assert(
//      mySelect.unsafeRunSync() == List(
//        Country("BRA", "Brazil", 170115000, Some(776739.00)),
//        Country("CHN", "China", 1277558000, Some(982268.00))
//      )
//    )
//
//  }

}
