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
  implicit val pointPlacement = InsideHyperBall
  val csvResultsFile = "curse-dimensions-distance-ball.csv"
  val dimensions = Vector(2,3,6,10, 50, 75, 100, 150, 200)

//  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying distribution graph */
  plotDistribution(experiments.toArray.toList,"ball")

}


