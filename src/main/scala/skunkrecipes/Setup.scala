package skunkrecipes

import cats.effect._
import skunk._
import natchez.Trace.Implicits.noop
import scala.concurrent.ExecutionContext

object Setup {
  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

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
