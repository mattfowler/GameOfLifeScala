package grid

import cell.{LiveCell, Cell, DeadCell}

final class Grid(private val dimension:Int) {

  private var grid:Array[Array[Cell]] = createGrid(dimension)

  def getCurrentGeneration:Array[Array[Cell]] = {
    return grid;
  }

  def createGrid(dimension:Int):Array[Array[Cell]] = {
    grid = Array.fill(dimension, dimension)(new DeadCell(List()))
    for (i <- 0 to dimension - 1) {
      for (j <- 0 to dimension - 1) {
        val neighbors = List(getNeighbor(i-1, j-1),
                             getNeighbor(i+1, j+1),
                             getNeighbor(i+1, j-1),
                             getNeighbor(i-1, j+1),
                             getNeighbor(i-1, j),
                             getNeighbor(i  , j-1),
                             getNeighbor(i+1, j),
                             getNeighbor(i  , j+1))
        grid(i)(j).neighbors = neighbors.filterNot(_ == null)
      }
    }
    return grid;
  }

  private def getNeighbor(row:Int, column:Int):Cell = {
    if (row < 0 || column < 0 || row > grid.length-1 || column > grid.length-1) {
      return null
    } else {
      return grid(row)(column)
    }
  }
}
