package experiments

import primitives.{PointPlacement, Point}

case class PairDistanceExperiment(dim: Int)
                                 (implicit val nPairs: Long, implicit val pointPlacement: PointPlacement)
  extends DistanceExperiment(dim) {

  val title: String = "PairDistance"

  val pairs = (1L to nPairs).map(_ => (Point(), Point()))

  val distances = pairs.map((pair: (Point, Point)) => pair._1.distanceTo(pair._2))
}