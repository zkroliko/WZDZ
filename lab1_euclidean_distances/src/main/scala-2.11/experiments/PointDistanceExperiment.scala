package experiments

import primitives.{PointPlacement, Point}

case class PointDistanceExperiment(dim: Int, source: Point)
                                  (implicit val nPairs: Long, implicit val pointPlacement: PointPlacement)
  extends DistanceExperiment(dim) {

  val title: String = "PointDistance"

  val points = (1L to nPairs).map(_ => Point())

  val distances = points.map(p => p.distanceTo(source))
}