package primitives

object Center {

  def apply(implicit dimensions: Int): Point = {
    Point((1 to dimensions).map(_ => 0.5))
  }
}
