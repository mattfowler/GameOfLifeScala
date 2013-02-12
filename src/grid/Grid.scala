package grid

import cell.{LiveCell, Cell, DeadCell}

final class Grid(private val dimension: Int) {

  private var grid: Array[Array[Cell]] = createGrid(dimension)

  def getCurrentGeneration: Array[Array[Cell]] = {
    return grid;
  }

  def addLiveCell(row:Int, column:Int) = {
    val cell = grid(row)(column)
    val newCell: Cell = liveCellWithUpdatedNeighbors(cell)
    grid(row)(column) = newCell
  }

  private def liveCellWithUpdatedNeighbors(oldCell:Cell): Cell = {
    val newCell = new LiveCell(oldCell.neighbors)

    newCell.neighbors.foreach {
      c: Cell =>
        var newCells = c.neighbors.filterNot(_.equals(oldCell))
        newCells = newCells :+ newCell
        c.neighbors = newCells
    }
    newCell
  }

  private def createGrid(dimension: Int): Array[Array[Cell]] = {
    grid = Array.fill(dimension, dimension)(new DeadCell(List()))
    for (i <- 0 to dimension - 1) {
      for (j <- 0 to dimension - 1) {
        val neighbors = List((i-1, j-1), (i+1, j+1), (i+1, j-1), (i-1, j+1),
                             (i-1, j)  , (i  , j-1), (i+1, j)  , (i  , j+1))
        grid(i)(j).neighbors = neighborList(neighbors)
      }
    }
    return grid;
  }

  private def neighborList(indices: List[(Int, Int)]): List[Cell] = {
    if (indices.isEmpty) {
      return Nil;
    } else {
      val rowAndCol = indices.head
      val row = rowAndCol._1
      val col = rowAndCol._2
      if (row < 0 || col < 0 || row > grid.length - 1 || col > grid.length - 1) {
        return neighborList(indices.drop(1))
      } else {
        return grid(row)(col) :: neighborList(indices.drop(1))
      }
    }
  }
}
