package plottingAddons

import experiments.HasADistribution

trait PlotsDistribution {

  def plotDistribution(experiments: List[HasADistribution], objDesc: String): Unit = {
    val title = s"Distribution of points / range from center of a hyper$objDesc"
    val f = breeze.plot.Figure(title)
    val p = f.subplot(0)
    p.legend = true
    p.title = title
    p.xlabel = "range"
    p.ylabel = "points within"
    experiments.foreach { e => e.createDistributionPlot(p) }
    f.saveas(s"$objDesc-center-range-distribution.png",200)
  }
}
