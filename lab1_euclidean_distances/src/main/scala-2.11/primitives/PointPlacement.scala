package primitives

trait PointPlacement {

  def generatePoint(implicit dimensions: Int): Point
}
