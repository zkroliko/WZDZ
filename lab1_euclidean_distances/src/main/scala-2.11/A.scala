object A extends App{

  val nPoints = 10000

  testForDimension(1)

  def testForDimension(dim : Int): Unit = {
    implicit val dimensions = dim
    val points = (1 to nPoints).map(_ => Point())
    points.foreach(println(_))
    println(points.map(_.length).sum/nPoints)
  }
}
