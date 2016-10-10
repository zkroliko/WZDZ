package primitives

import primitives.pointPlacement.PointPlacement


object Point {
  val generator = scala.util.Random

  def apply(coords : Seq[Double])(implicit dimensions: Int) : Point = {
    new Point(coords)
  }

  def apply()(implicit dimensions: Int, pointPlacement: PointPlacement) : Point = {
    pointPlacement.generatePoint(dimensions)
  }

}

class Point(val coords : Seq[Double])(implicit dimensions: Int) extends AbstractPoint[Double](coords){

  def this()(implicit dimensions: Int) {
    this((1 to dimensions).map(_ => Point.generator.nextDouble()))
  }

  // Sorting up to minimize numerical error
  def length: Double = Math.sqrt(coords.map(c => c*c).sortWith(_ < _).sum)

  // Sorting up to minimize numerical error
  def distanceTo(other: Point): Double = {
    Math.sqrt((this.coords,other.coords).zipped.map((a,b) => (a-b)*(a-b)).sortWith(_ < _).sum)
  }

  def /(value : Double) = new Point(coords.map(_/value))

  def *(value : Double) = new Point(coords.map(_*value))

  def +(other: Point ) = new Point(coords.zip(other.coords).map(e => e._1+e._2))

  def -(other: Point ) = new Point(coords.zip(other.coords).map(e => e._1-e._2))

}

abstract class AbstractPoint[T : Numeric](coords : Seq[T])(implicit dimensions: Int) {
  require(coords.length == dimensions)

  override def toString: String = s"[${coords.map(_.toString).reduce(_ + ", " + _)}]"
}
