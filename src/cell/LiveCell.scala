package cell

final class LiveCell(neighbors:List[Cell]) extends Cell(neighbors) {

  override def isLive:Boolean = true

  override def getNextGeneration:Cell = {
    if (liveNeighborCount < 2 || liveNeighborCount > 3) {
      return new DeadCell(neighbors)
    }
    this
  }
}