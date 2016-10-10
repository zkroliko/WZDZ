package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import plottingAddons.{PlotsHistogram, PlotsDistribution}
import primitives.Center
import primitives.pointPlacement.InsideHyperCube
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object CenterDistanceCube extends App with PlotsDistribution with PlotsHistogram{

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "curse-dimensions-distance-cube.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  //  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  Future {
    plotHistogram(experiments.toArray.toSeq,"dist-center-cube",s"Histogram of distance from the middle of a hypercube")
  }

  /* Displaying distribution graph */
  plotDistribution(experiments.toArray.toSeq, "cube")
}


