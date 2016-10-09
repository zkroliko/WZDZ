package primitives.pointPlacement

import primitives.Point

object InsideHyperCube extends PointPlacement{

  // Kind of a identity function
  def generatePoint(implicit dimensions: Int): Point = new Point()
}
