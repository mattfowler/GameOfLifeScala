package cell

final class EmptyCell (neighbors:List[Cell]) extends Cell(neighbors) {
  def getNextGeneration: Cell = this

  def isLive: Boolean = false
}
