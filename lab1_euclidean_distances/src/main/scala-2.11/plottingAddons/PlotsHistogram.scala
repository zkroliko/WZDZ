package plottingAddons

import experiments.DistanceExperiment

trait PlotsHistogram {
  def plotHistogram(experiments: Seq[DistanceExperiment], filename: String, desc: String): Unit = {
    val f = breeze.plot.Figure(desc)
    val p = f.subplot(0)
    p.legend = true
    p.title = desc
    p.xlabel = "distance"
    p.ylabel = "points"
    experiments.foreach { e => e.createHistogram(p) }
    f.saveas(s"$filename-histogram.png",200)
  }
}
