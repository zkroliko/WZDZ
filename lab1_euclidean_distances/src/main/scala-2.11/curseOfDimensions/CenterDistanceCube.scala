package curseOfDimensions

import breeze.linalg._
import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import plottingAddons.PlotsDistribution
import primitives.Center
import primitives.pointPlacement.InsideHyperCube

object CenterDistanceCube extends App with PlotsDistribution{

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "curseOfDimensionsDistanceDensityCube.csv"
  val dimensions = Vector(2, 10, 50, 100, 150, 200)

  //  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying histograms */
  //  experiments.foreach { e => e.createHistogram() }

  /* Displaying distribution graph */
  plotDistribution(experiments.toArray.toSeq,"cube")
}


