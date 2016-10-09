package plottingAddons

import experiments.DistanceExperiment

trait PlotsHistogram {
  def plotHistogram(experiments: Seq[DistanceExperiment], objDesc: String): Unit = {
    val title = s"Histogram of distance from center of a hyper$objDesc"
    val f = breeze.plot.Figure(title)
    val p = f.subplot(0)
    p.legend = true
    p.title = title
    p.xlabel = "distance"
    p.ylabel = "points"
    experiments.foreach { e => e.createHistogram(p) }
    f.saveas(s"$objDesc-range-histogram.png")
  }
}
