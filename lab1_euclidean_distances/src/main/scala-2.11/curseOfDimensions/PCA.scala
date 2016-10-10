package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.{PCAExperiment, PointDistanceExperiment}
import plottingAddons.{PlotsPCAResultsTogether, PlotsPCAResults, PlotsDistribution, PlotsHistogram}
import primitives.Center
import primitives.pointPlacement.{InsideHyperBall, InsideHyperCube}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PCA extends App with PlotsDistribution with PlotsPCAResults with PlotsPCAResultsTogether{

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "curse-dimensions-distance-cube.csv"
  val dimensions = Vector(2, 3, 4)

  //  /* Running experiments sequentially */
  val experimentsCube = dimensions.map(dim => PCAExperiment(dim,Center(dim))(nPairs,InsideHyperCube))
  val experimentsBall = dimensions.map(dim => PCAExperiment(dim,Center(dim))(nPairs,InsideHyperBall))

  /* Writing results in human readable format */
  experimentsCube.foreach(println(_))
  experimentsBall.foreach(println(_))

  /* Writing to result file */
  ResultsWriter("pca-distance-cube.csv",experimentsCube.toArray.toList)
  ResultsWriter("pca-distance-ball.csv",experimentsBall.toArray.toList)

  /* Displaying histograms */
//  plotPCAResults(experimentsCube.toArray.toSeq,"cube",s"PCA hypercube")
//  plotPCAResults(experimentsBall.toArray.toSeq,"ball",s"PCA ball")
  plotPCAResults(experimentsCube.toArray.toSeq,experimentsBall.toArray.toSeq)

  /* Displaying distribution graph */
//  plotPCAResults(experiments.toArray.toSeq, "cube")
}


