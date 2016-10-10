package experiments

import breeze.plot._
import primitives.Point
import primitives.pointPlacement.PointPlacement
import utils.MatrixTools

case class PCAExperiment(dim: Int, source: Point)
                        (implicit val nPoints: Long, implicit val pointPlacement: PointPlacement)
  extends DistanceExperiment(dim) with HasADistribution {

  val title: String = "PCA-2D-Distance"

  val orgPoints = (1L to nPoints).map(_ => Point())

  val matrix = MatrixTools.pointsToMatrix(orgPoints)

  val points = new breeze.linalg.PCA(matrix, MatrixTools.cov(matrix.t))

  val distances = MatrixTools.pointsFromMatrix(points.scores).map(p => p.distanceTo(source))

  def createPCAPlot(p: Plot): Unit = {
    p += breeze.plot.plot(points.scores(::,0), points.scores(::,1), '.', name=s"$dim")
  }

  override def createDistributionPlot(p: Plot)= {
    val nSteps = 200
    val steps = breeze.linalg.linspace(0, distances.max, nSteps)
    val counts = steps.map(s => distances.count(_ < s).toDouble)
    p += plot(steps,counts.toVector,'-',name=s"$dim dimensions")
  }

}