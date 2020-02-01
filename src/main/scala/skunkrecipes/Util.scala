package skunkrecipes

import java.io.{File, PrintWriter}
import cats.implicits._
import com.github.gekomad.ittocsv.core.Header.{FieldNames, csvHeader}
import com.github.gekomad.ittocsv.parser.IttoCSVFormat
import cats.effect.IO
import skunk.implicits._
import skunk.codec.all._
import scala.language.reflectiveCalls

object Util {
  val tmpDir: String = System.getProperty("java.io.tmpdir")

  def deleteFile(filename: String): Boolean = { new File(filename).delete() }

  def autoclose[A <: { def close(): Unit }, B](resource: IO[A])(f: A => IO[B]): IO[B] =
    resource.bracket(f) { closable =>
      IO(closable.close())
        .handleErrorWith(_ => IO.unit)
        .void
    }

  def writeIttoHeaderTofile[A: FieldNames](fileName: String)(implicit csvFormat: IttoCSVFormat): IO[Unit] =
    autoclose(IO(new PrintWriter(new File(fileName)))) { fis =>
      fis.write(csvHeader[A])
      IO.unit
    }

  def dropCreateTableTableEnum(): IO[Int] = {
    val drop = sql"""DROP TABLE IF EXISTS table_enum""".query(int4)

    val create =
      sql"""CREATE TABLE table_enum (
            id   int,
            product_type VARCHAR NOT NULL)""".query(int4)

    Setup.session.use { s =>
      s.unique(drop) *> s.unique(create)

    }
  }

  def dropCreateTablePerson(): IO[Int] = {
    val drop = sql"""DROP TABLE IF EXISTS person""".query(int4)

    val create =
      sql""" 
           | CREATE TABLE person (
           | id   SERIAL,
           | name VARCHAR NOT NULL UNIQUE,
           | age  SMALLINT)
           | """.query(int4)

    Setup.session.use { s =>
      s.unique(drop) *> s.unique(create)
    }
  }

  def dropCreateTablePersonPets(): IO[Int] = {
    val drop = sql"DROP TABLE IF EXISTS person_pets".query(int4)

    val create =
      sql"""
           | CREATE TABLE person_pets (
           | id SERIAL,
           | name VARCHAR   NOT NULL UNIQUE,
           | pets VARCHAR[] NOT NULL)
           | """.query(int4)
    Setup.session.use { s =>
      s.unique(drop) *> s.unique(create)
    }
  }
}
