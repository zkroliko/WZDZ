package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import plottingAddons.{PlotsAverageDistance, PlotsDistribution, PlotsHistogram}
import primitives.Center
import primitives.pointPlacement.InsideHyperBall

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object CenterDistanceAvgBall extends App with PlotsAverageDistance {

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperBall
  val csvResultsFile = "curse-dimensions-avg-distance-ball.csv"
  val dimensions = 3 to 200

//  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying avg distance graph */
  plotAverageDistance(experiments.toArray.toSeq,"ball")

}


