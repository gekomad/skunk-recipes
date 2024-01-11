package skunkrecipes

import cats.effect._
import skunk._
import org.typelevel.otel4s.trace.Tracer.Implicits.noop

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
