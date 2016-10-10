package primitives.pointPlacement

import primitives.Point

import scala.util.Random

object OnHyperSphere extends PointPlacement{

  val random = Random

  /**
    * https://math.stackexchange.com/questions/87230/picking-random-points-in-the-volume-of-sphere-with-uniform-probability
 *
    * @param dimensions number of dimensions for a point
    * @return a point inside a ball
    */
  def generatePoint(implicit dimensions: Int): Point = {
    val U = math.pow(random.nextDouble(), 1/dimensions)
    val X = ( 0 until dimensions) map {_ => random.nextGaussian()}
    Point(X.map(xo=>0.5 + (0.5*U/ math.sqrt(X.map(x => x * x).sum))*xo))
  }
}
