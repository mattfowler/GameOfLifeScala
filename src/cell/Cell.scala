package cell

abstract class Cell(var neighbors:List[Cell]) {

  def getNextGeneration:Cell

  def isLive:Boolean

  def liveNeighborCount:Integer = neighbors.count(_.isLive)
}
