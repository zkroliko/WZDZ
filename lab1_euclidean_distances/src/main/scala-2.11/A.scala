object A extends App{

  val nPairs = 1000

  val dimensions = Vector(2,10,50,100,150,200)

  val experiments = dimensions.foreach(DimensionExperiment)

  case class DimensionExperiment(val dim : Int) {
    implicit val dimensions = dim
    val pairs = (1 to nPairs).map(_ => (Point(),Point()))
    val distances = pairs.map((pair :( Point, Point)) => pair._1.distanceTo(pair._2))
    // Sorting up to minimize numerical error
    val averageDistance = distances.sortWith(_ < _).sum / nPairs
    println(averageDistance)
  }
}
