//package selecting
//
//import org.scalatest.funsuite.AnyFunSuite
//import skunk.Decoder
//
//class ShapelessRecord extends AnyFunSuite {
//
//  test("shapeless record") { TODO
//    import shapeless._
//    import shapeless.record.Record
//    import skunk.implicits._
//    import skunk.codec.all._
//    import skunkrecipes.Setup
//
//    type Rec = Record.`'code -> String, 'name -> String, 'pop -> Int, 'gnp -> Option[BigDecimal]`.T
//
//    val country: Decoder[Rec] = (bpchar(3) ~ varchar ~ int4 ~ numeric(10, 2).opt).gmap[Rec]
//    val query                 = sql"select code, name, population, gnp from country order by code limit 3".query(country)
//
//    val mySelect = Setup.session.use(_.execute(query))
//
//    assert(
//      mySelect.unsafeRunSync == List(
//        "ABW" :: "Aruba" :: 103000 :: Some(828.00) :: HNil,
//        "AFG" :: "Afghanistan" :: 22720000 :: Some(5976.00) :: HNil,
//        "AGO" :: "Angola" :: 12878000 :: Some(6648.00) :: HNil
//      )
//    )
//  }
//
//}
