package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import plottingAddons.{PlotsHistogram, PlotsDistribution}
import primitives.Center
import primitives.pointPlacement.InsideHyperBall
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object CenterDistanceBall extends App with PlotsDistribution with PlotsHistogram {

  implicit val nPairs = 1500L
  implicit val pointPlacement =InsideHyperBall
  val csvResultsFile = "curse-dimensions-distance-ball.csv"
  val dimensions = Vector(2, 5, 7, 9, 11, 13)

//  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  Future {
    plotHistogram(experiments.toArray.toSeq, "ball", s"Histogram of distance from the middle of a hyperball")
  }

  /* Displaying distribution graph */
  plotDistribution(experiments.toArray.toSeq,"ball")

}


