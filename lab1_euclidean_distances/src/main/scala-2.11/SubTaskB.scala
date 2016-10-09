import breeze.linalg._
import csvResults.ResultsWriter
import experiments.VertexDistanceExperiment
import primitives.Vertex

object SubTaskB extends App {

  implicit val nPairs = 1000L
  val csvResultsFile = "resultsB.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => VertexDistanceExperiment(dim,Vertex.bottomLeftBack(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  experiments.foreach { e => e.createHistogram() }

}


