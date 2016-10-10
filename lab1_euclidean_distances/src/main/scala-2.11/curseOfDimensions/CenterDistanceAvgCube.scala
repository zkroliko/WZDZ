package curseOfDimensions

import csvResults.ResultsWriter
import experiments.PointDistanceExperiment
import plottingAddons.PlotsAverageDistance
import primitives.Center
import primitives.pointPlacement.{InsideHyperCube, InsideHyperBall}

object CenterDistanceAvgCube extends App with PlotsAverageDistance {

  implicit val nPairs = 1500L
  implicit val pointPlacement = InsideHyperCube
  val csvResultsFile = "curse-dimensions-avg-distance-cube.csv"
  val dimensions = 3 to 200

//  /* Running experiments sequentially */
  val experiments = dimensions.map(dim => PointDistanceExperiment(dim,Center(dim)))

  /* Writing results in human readable format */
  experiments.foreach(println(_))

  /* Writing to result file */
  ResultsWriter(csvResultsFile,experiments.toArray.toList)

  /* Displaying avg distance graph */
  plotAverageDistance(experiments.toArray.toSeq,"cube")

}


