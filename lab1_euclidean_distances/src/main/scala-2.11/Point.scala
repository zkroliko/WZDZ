import scala.util.Random


object Point {

  val generator = scala.util.Random

  def apply()(implicit dimensions: Int = 1) : Point = {
    new Point((1 to dimensions).map(_ => generator.nextDouble()))
  }
}

class Point(coords : Seq[Double])(implicit dimensions: Int = 1) extends AbstractPoint[Double](coords){

  // Sorting up to minimize numerical error
  def length: Double = Math.sqrt(coords.map(c => c*c).sortWith(_ < _).sum)

}

abstract class AbstractPoint[T : Numeric](coords : Seq[T])(implicit dimensions: Int = 1) {
  require(coords.length == dimensions)

  override def toString: String = s"[${coords.map(_.toString).reduce(_ + ", " + _)}]"

}
