package selecting

import org.scalatest.funsuite.AnyFunSuite
import skunk.~

class RowMappings extends AnyFunSuite {

  test("row mappings") {
    import shapeless._
    import skunk.implicits._
    import skunk.codec.all._
    import skunkrecipes.Setup

    val query =
      sql"select code, name, population, gnp from country order by code limit 3"
        .query(bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt)
        .map { case a ~ b ~ c ~ d => HList(a, b, c, d) }

    val mySelect = Setup.session.use(_.execute(query))

    assert(
      mySelect.unsafeRunSync == List(
        "ABW" :: "Aruba" :: 103000 :: Some(828.00) :: HNil,
        "AFG" :: "Afghanistan" :: 22720000 :: Some(5976.00) :: HNil,
        "AGO" :: "Angola" :: 12878000 :: Some(6648.00) :: HNil
      )
    )
  }

}
