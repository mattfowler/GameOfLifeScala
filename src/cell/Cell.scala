package cell

abstract class Cell(private val neighbors:List[Cell]) {

  def getNextGeneration:Cell

  def isLive:Boolean

  def liveNeighborCount:Integer = {
    neighbors.count(_.isLive)
  }

  override def equals(that : Any):Boolean = {
    that match {
      case that: Cell => this.isLive == that.isLive &&
                         this.neighbors.equals(that.neighbors)
      case _  => false
    }
  }
}
