package skunkrecipes

import cats.effect._
import skunk._
import natchez.Trace.Implicits.noop

object Setup {

  val session: Resource[IO, Session[IO]] =
    Session.single[IO](
      host = "localhost",
      port = 5436,
      user = "jimmy",
      database = "world",
      password = Some("banana"),
      debug = false
    )

}
