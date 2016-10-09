package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import primitives.Center
import primitives.pointPlacement.{InsideHyperBall, InsideHyperCube}

object CenterDistanceBall extends App {

  implicit val nPairs = 1500L
  implicit val pointPlacement =InsideHyperBall
  val csvResultsFile = "curseOfDimensionsDistanceDensityBall.csv"
  val dimensions = Vector(2, 5, 7, 9, 11, 13)

//  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  experiments.foreach { e => e.createHistogram() }

  /* Displaying distributions */
  experiments.foreach{e => e.createDistributionPlot()}


}


