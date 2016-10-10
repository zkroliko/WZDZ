package plottingAddons

import experiments.{PCAExperiment, PointDistanceExperiment}

trait PlotsPCAResults {
  def plotPCAResults(experiments: Seq[PCAExperiment],filename: String, objDesc: String): Unit = {
    val title = s"PCA hyper$objDesc"
    val f = breeze.plot.Figure(title)
    val p = f.subplot(0)
    p.legend = true
    p.title = title
    p.xlabel = "a"
    p.ylabel = "b"
    experiments.foreach { e => e.createPCAPlot(p) }
    f.saveas(s"$filename-pca.png")
  }
}
