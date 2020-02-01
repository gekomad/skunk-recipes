package selecting

import cats.effect.IO
import org.scalatest.funsuite.AnyFunSuite

class Count extends AnyFunSuite {

  test("select count") {
    import skunk._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    val query: Query[Void, Long] = sql"select count(1) from country".query(int8)

    val aa: IO[Long] = Setup.session.use(_.unique(query))

    assert(aa.unsafeRunSync == 239)
  }

}
