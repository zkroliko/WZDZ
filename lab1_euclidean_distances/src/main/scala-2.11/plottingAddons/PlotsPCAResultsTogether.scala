package plottingAddons

import experiments.PCAExperiment
import scala.concurrent.ExecutionContext.Implicits.global


import scala.concurrent.Future

trait PlotsPCAResultsTogether {
  def plotPCAResults(cubes: Seq[PCAExperiment], balls: Seq[PCAExperiment]): Unit = {
    cubes.zip(balls).foreach { pair: (PCAExperiment,PCAExperiment) =>
      val title = s"PCA hypercube, hyperball"
      val f = breeze.plot.Figure(title)
      val p = f.subplot(0)
      p.legend = true
      p.title = title
      p.xlabel = "x"
      p.ylabel = "y"
      pair._1.createPCAPlot(p)
      pair._2.createPCAPlot(p)
      f.saveas(s"cube-ball-together-pca-${pair._1.dim}.png")
    }
  }
}
