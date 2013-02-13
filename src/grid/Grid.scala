package grid

import cell.{LiveCell, Cell, DeadCell}

final class Grid(private val dimension: Int) {

  private val grid: Array[Array[Cell]] = createGrid(dimension)

  def getCurrentGeneration: Array[Array[Cell]] = grid

  def getNextGeneration: Array[Array[Cell]] = {
    for (i <- 0 to dimension - 1) {
      for (j <- 0 to dimension - 1) {
        grid(i)(j) = grid(i)(j).getNextGeneration
      }
    }
    updateNeighbors(grid)
  }

  def setLiveCellAt(row:Int, column:Int) =
    grid(row)(column) = newCellWithUpdatedNeighbors(grid(row)(column), true)

  def setDeadCellAt(row:Int, column:Int) =
    grid(row)(column) = newCellWithUpdatedNeighbors(grid(row)(column), false)

  private def newCellWithUpdatedNeighbors(oldCell:Cell, isLive:Boolean): Cell = {
    val newCell = if(isLive) new LiveCell(oldCell.neighbors) else new DeadCell(oldCell.neighbors)

    newCell.neighbors.foreach { c: Cell =>
        var newCells = c.neighbors.filterNot(_.equals(oldCell))
        newCells = newCells :+ newCell
        c.neighbors = newCells
    }
    newCell
  }

  private def createGrid(dimension: Int): Array[Array[Cell]] = {
    val grid:Array[Array[Cell]] = Array.fill(dimension, dimension)(new DeadCell(List()))
    updateNeighbors(grid)
  }

  private def updateNeighbors(grid:Array[Array[Cell]]):Array[Array[Cell]] = {
    for (i <- 0 to dimension - 1) {
      for (j <- 0 to dimension - 1) {
        val neighborIndices = for(r <- i-1 to i+1; c <- j-1 to j+1) yield (r, c)
        grid(i)(j).neighbors = neighborList(neighborIndices.filter(!_.equals((i,j))), grid)
      }
    }
    grid
  }

  private def neighborList(indices: IndexedSeq[(Int, Int)], grid:Array[Array[Cell]]): List[Cell] = {
    if (indices.isEmpty) {
      Nil
    } else {
      val rowAndCol = indices.head
      val row = rowAndCol._1
      val col = rowAndCol._2
      if (row < 0 || col < 0 || row > grid.length - 1 || col > grid.length - 1) {
        neighborList(indices.drop(1), grid)
      } else {
        grid(row)(col) :: neighborList(indices.drop(1), grid)
      }
    }
  }
}
