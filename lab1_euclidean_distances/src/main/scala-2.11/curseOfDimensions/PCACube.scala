package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.{PCAExperiment, PointDistanceExperiment}
import plottingAddons.{PlotsPCAResults, PlotsDistribution, PlotsHistogram}
import primitives.Center
import primitives.pointPlacement.InsideHyperCube

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PCACube extends App with PlotsDistribution with PlotsPCAResults{

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "curse-dimensions-distance-cube.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  //  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PCAExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
    plotPCAResults(experiments.toArray.toSeq,"cube",s"PCA hypercube")

  /* Displaying distribution graph */
//  plotPCAResults(experiments.toArray.toSeq, "cube")
}


