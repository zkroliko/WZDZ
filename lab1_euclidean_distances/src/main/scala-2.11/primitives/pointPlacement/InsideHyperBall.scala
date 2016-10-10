package primitives.pointPlacement

import breeze.linalg.{*, norm}
import primitives.{Center, Point}
import primitives.pointPlacement.OnHyperSphere

import scala.util.Random

object InsideHyperBall extends PointPlacement{

  val random = Random

  /**
    * https://math.stackexchange.com/questions/87230/picking-random-points-in-the-volume-of-sphere-with-uniform-probability
    *
    * @param dimensions number of dimensions for a point
    * @return a point inside a ball
    */
  def generatePoint(implicit dimensions: Int): Point = {
    val p: Point = OnHyperSphere.generatePoint
    val c = Center(dimensions)
    val r = random.nextDouble()/2
    (p-c)*r+c
  }
}
