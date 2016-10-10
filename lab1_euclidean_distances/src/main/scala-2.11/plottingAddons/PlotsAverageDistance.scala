package plottingAddons

import breeze.linalg.dim
import breeze.plot._
import experiments.PointDistanceExperiment

trait PlotsAverageDistance {
  def plotAverageDistance(experiments: Seq[PointDistanceExperiment], objDesc: String): Unit = {
    val title = s"Average distance of points from center of a hyper$objDesc"
    val f = breeze.plot.Figure(title)
    val p = f.subplot(0)
    p.legend = true
    p.title = title
    p.xlabel = "dimensions"
    p.ylabel = "average distance"
    val points = experiments.map{_.meanDistance}
    p += plot((1 to points.length).map{_.toDouble},points,'-')
    f.saveas(s"$objDesc-center-range-average.png",200)
  }
}
