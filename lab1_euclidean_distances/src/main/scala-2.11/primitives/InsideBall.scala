package primitives

import breeze.linalg.dim

trait InsideBall {
  self: Point =>

  implicit val dimension: Int

  val center = new Point((1 to dimension).map(_ => 0.0).toSeq)(dimension)

  def generatePoint(): Point = {
    var p = Point()
    while(center.distanceTo(p) < 0.5) {
      p = Point()
    }
    p
  }
}
