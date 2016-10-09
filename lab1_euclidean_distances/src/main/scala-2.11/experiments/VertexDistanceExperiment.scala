package experiments

import primitives.Point

case class VertexDistanceExperiment(dim: Int, vertex: Point)(implicit val nPairs: Long)
  extends DistanceExperiment(dim) {

  val title: String = "VertexDistance"

  val points = (1L to nPairs).map(_ => Point())

  val distances = points.map(p => p.distanceTo(vertex))
}