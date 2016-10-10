package utils

import breeze.linalg._
import primitives.Point

object MatrixTools {

  def pointsToMatrix(points: Seq[Point])(implicit dim: Int)  = {
    val matrix = DenseMatrix.zeros[Double](points.length, dim)
    for (x <- points.indices) {
      for (y <- 0 until dim) {
        matrix.update(x,y,points(x).coords(y))
      }
    }
    matrix
  }

  def pointsFromMatrix(matrix: Matrix[Double])(implicit dim: Int) : List[Point] = {
    val buf = List[Point]()
    (0 to matrix.cols).foreach { x =>
      buf :+ new Point((0 to dim-1).map(y => matrix(x,y)))(dim)
    }
    buf
  }

  def cov(A:DenseMatrix[Double]):DenseMatrix[Double] = {
    val n = A.cols
    val D:DenseMatrix[Double] = A.copy
    val mu:DenseVector[Double] = sum(D,Axis._1):*(1.0/n) // sum along rows --> col vector
    (0 until n).map(i => D(::,i):-= mu)
    val C = (D*D.t):*(1.0/n)
    C
  }
}
