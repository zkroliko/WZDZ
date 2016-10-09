package experiments

import breeze.plot._


abstract class DistanceExperiment(dim : Int)(implicit val size: Long) extends WriteableAsCSV{
  implicit val dimensions = dim
  val distances : Seq[Double]

  val title: String

  // Sorting up to minimize numerical error
  lazy val meanDistance = distances.sortWith(_ < _).sum / size
  lazy val variance = Math.sqrt(distances.map(d => (d - meanDistance)*(d - meanDistance)).sortWith(_ < _).sum) / (size - 1)

  override def toString: String = {
    s"""--------------------------------------
        |Test for $size $dim-dimensional points
        |Mean distance: $meanDistance
        |Variance: $variance"""".stripMargin
  }

  def toCSV: String = {
    s"$dim, $meanDistance, $variance"
  }

  def createHistogram() = {
    val f = breeze.plot.Figure(s"Hist $dim dimensions")
    val p = f.subplot(0)
    p += breeze.plot.hist(distances,100)
    p.title = s"$title, Histogram for $dim dimensions"
    f.saveas(s"$title-$dimensions.png")
  }

  def createDistributionPlot()= {
    val nSteps = 100
    val steps = breeze.linalg.linspace(0, distances.max, nSteps)
    // Could be done with less complexity using clever data structures
    val counts = steps.map(s => distances.count(_ < s).toDouble)
    val f = breeze.plot.Figure("Distribution of points / range")
    val p = f.subplot(0)
    p += plot(steps,counts.toVector,'-')
    p.title = s"Dist $dim dimensions"
    f.saveas(s"$title-distribution-$dimensions.png")
  }
}