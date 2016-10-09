package experiments

import primitives.Point

case class PairDistanceExperiment(dim: Int)(implicit val nPairs: Long)
  extends DistanceExperiment(dim) {

  val pairs = (1L to nPairs).map(_ => (Point(), Point()))

  val distances = pairs.map((pair: (Point, Point)) => pair._1.distanceTo(pair._2))

}