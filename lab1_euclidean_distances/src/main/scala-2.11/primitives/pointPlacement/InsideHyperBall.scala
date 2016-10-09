package primitives.pointPlacement

import primitives.{Center, Point}

object InsideHyperBall extends PointPlacement{

  def generatePoint(implicit dimensions: Int): Point = {
    var p = new Point()
    while(Center(dimensions).distanceTo(p) > 0.5) {
      p = new Point()
    }
    p
  }
}
