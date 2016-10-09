import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PairDistanceExperiment
import primitives.{InsideHyperBall, Point}

object CurseOfDimensionsTask extends App {

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperBall
  val csvResultsFile = "curseOfDimensions.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  implicit val dim = 200
  val points = (1L to nPairs).map(_ => Point())

//  /* Running experiments sequentially */
//  val experiments = dimensions.map(dim => PairDistanceExperiment(dim))
//
//  /* Writing results in human readable format */
//  experiments.foreach(println(_))
//
//  /* Writing to result file */
//  ResultsWriter(csvResultsFile,experiments.toArray.toList)
//
//  /* Displaying histograms */
//  experiments.foreach { e => e.createHistogram() }


}


