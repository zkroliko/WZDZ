package experiments

abstract class DistanceExperiment(dim : Int)(implicit val size: Long) extends WriteableAsCSV{
  implicit val dimension = dim
  val distances : Seq[Double]

  // Sorting up to minimize numerical error
  lazy val meanDistance = distances.sortWith(_ < _).sum / size
  lazy val variance = Math.sqrt(distances.map(d => (d - meanDistance)*(d - meanDistance)).sortWith(_ < _).sum) / (size - 1)

  override def toString: String = {
    s"""--------------------------------------
        |Test for $size $dim-dimensional pairs
        |Mean distance: $meanDistance
        |Variance: $variance"""".stripMargin
  }

  def toCSV: String = {
    s"$dim, $meanDistance, $variance"
  }

  def createHistogram() = {
    val f = breeze.plot.Figure()
    val p = f.subplot(0)
    p += breeze.plot.hist(distances,100)
    p.title = s"Histogram for $dim dimensions"
  }
}