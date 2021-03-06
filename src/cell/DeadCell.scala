package cell

final class DeadCell(neighbors:List[Cell]) extends Cell(neighbors) {

  override def isLive:Boolean = false

  override def getNextGeneration:Cell = {
    if (liveNeighborCount == 3) {
      return new LiveCell(neighbors)
    }
    this
  }
}
