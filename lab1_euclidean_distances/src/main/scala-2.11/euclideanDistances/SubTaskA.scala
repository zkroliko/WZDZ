package euclideanDistances

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PairDistanceExperiment
import plottingAddons.PlotsHistogram
import primitives.pointPlacement.InsideHyperCube

object SubTaskA extends App with PlotsHistogram{

  implicit val nPairs = 1000L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "euclidean-distancesA.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PairDistanceExperiment(dim))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  plotHistogram(experiments.toArray.toSeq,"dist-pairs-cube",s"Histogram of distance between pairs inside a hypercube")

}


