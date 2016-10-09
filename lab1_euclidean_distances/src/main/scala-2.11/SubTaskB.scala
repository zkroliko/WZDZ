import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import primitives.{InsideHyperCube, InsideHyperBall, Vertex}

object SubTaskB extends App {

  implicit val nPairs = 1000L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "resultsB.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Vertex.bottomLeftBack(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  experiments.foreach { e => e.createHistogram() }

}


