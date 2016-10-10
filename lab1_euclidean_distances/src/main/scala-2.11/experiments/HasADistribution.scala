package experiments

import breeze.plot.Plot

trait HasADistribution {
  def createDistributionPlot(p: Plot)
}
