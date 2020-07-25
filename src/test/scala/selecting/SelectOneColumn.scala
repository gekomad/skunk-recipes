package selecting

import cats.effect.IO
import org.scalatest.funsuite.AnyFunSuite

class SelectOneColumn extends AnyFunSuite {

  import skunk._
  import skunk.implicits._
  import skunk.codec.all._
  import skunkrecipes.Setup

  test("select one column") {

    val query: Query[Void, String] = sql"select name from country order by name limit 2".query(varchar)

    val aa: IO[List[String]] = Setup.session.use(_.execute(query))

    assert(aa.unsafeRunSync() == List("Afghanistan", "Albania"))
  }
}
