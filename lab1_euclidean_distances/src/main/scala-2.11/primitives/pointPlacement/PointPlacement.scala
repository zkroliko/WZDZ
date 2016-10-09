package primitives.pointPlacement

import primitives.Point

trait PointPlacement {

  def generatePoint(implicit dimensions: Int): Point
}
