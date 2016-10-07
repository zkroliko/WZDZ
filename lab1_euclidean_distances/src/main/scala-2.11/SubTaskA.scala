import java.io.PrintWriter

object SubTaskA extends App{

  implicit val nPairs = 1000L
  val csvResultsFile = "resultsA.csv"
  val dimensions = Vector(2,10,50,100,150,200)

  // Running experiments sequentially
  val experiments = dimensions.map(DistanceExperiment)

  // Writing results in human readable format
  experiments.foreach(println(_))

  // Writing to result file
  new PrintWriter(csvResultsFile) {
    write(experiments.foldLeft("")((s,e) => s"$s${e.toCSV}\n"))
    close()
  }
  println(s"\nResults written as CSV to file $csvResultsFile")


  case class DistanceExperiment(val dim : Int) {
    implicit val dimension = dim
    val pairs = (1L to nPairs).map(_ => (Point(),Point()))
    val distances = pairs.map((pair :( Point, Point)) => pair._1.distanceTo(pair._2))

    // Sorting up to minimize numerical error
    val meanDistance = distances.sortWith(_ < _).sum / nPairs
    val variance = Math.sqrt(distances.map(d => (d - meanDistance)*(d - meanDistance)).sortWith(_ < _).sum) / (nPairs - 1)

    override def toString: String = {
      s"""--------------------------------------
          |Test for $nPairs $dim-dimensional pairs
          |Mean distance: $meanDistance
          |Variance: $variance"""".stripMargin
    }

    def toCSV: String = {
      s"$dim, $meanDistance, $variance"
    }
  }
}


