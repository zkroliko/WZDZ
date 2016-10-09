package primitives

object Vertex {
  def bottomLeftBack(dim: Int) = {
    new Vertex((1 to dim).map(_ => 0.0).toSeq)(dim)
  }

}

class Vertex private(coords : Seq[Double])(implicit dim: Int) extends Point(coords){}


