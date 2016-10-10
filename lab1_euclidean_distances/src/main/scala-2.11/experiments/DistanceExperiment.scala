package experiments

import breeze.numerics.sqrt
import breeze.plot._

abstract class DistanceExperiment(dim: Int)(implicit val size: Long) extends WriteableAsCSV with HasADistribution {
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

  def createHistogram(p: Plot) = {
    p += breeze.plot.hist(distances.map(d => d/Math.sqrt(dim)),150,name=s"$dim dimensions")
//        p += breeze.plot.hist(distances.map(_/Math.sqrt(size)),150,name=s"$dim dimensions")

  }

  def createDistributionPlot(p: Plot)= {
    val nSteps = 200
    val steps = breeze.linalg.linspace(0, distances.max, nSteps)
    val counts = steps.map(s => distances.count(_ < s).toDouble)
    p += plot(steps,counts.toVector,'-',name=s"$dim dimensions")
  }
}