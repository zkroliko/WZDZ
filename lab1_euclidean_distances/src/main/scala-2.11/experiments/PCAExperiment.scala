package experiments

import breeze.linalg
import breeze.linalg.DenseMatrix
import breeze.plot.{Plot, Figure}
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

  val distances = orgPoints.map(p => p.distanceTo(source))

  def createPCAPlot(p: Plot): Unit = {
    p += breeze.plot.plot(points.scores(::,0), points.scores(::,1), '.', name=s"$dim")
  }

}