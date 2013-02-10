package cell

import org.scalatest.{FunSpec, FlatSpec}
import org.scalatest.matchers.ShouldMatchers

class CellTest extends FlatSpec with ShouldMatchers {

  it should "be able to check equality" in {

    val deadCellNoNeighbors = new DeadCell(List())
    val liveCellNoNeighbors = new LiveCell(List())

    liveCellNoNeighbors.equals(deadCellNoNeighbors) should equal(false)
    liveCellNoNeighbors.equals(5) should equal(false)
    liveCellNoNeighbors.equals(liveCellNoNeighbors) should equal(true)

    val neighborList = List(new LiveCell(List()), new DeadCell(List()))
    val liveCellTwoNeighbors = new LiveCell(neighborList)

    liveCellTwoNeighbors.equals(liveCellNoNeighbors) should equal(false)
  }

  it should "be able to get the number of live members" in {
    val emptyNeighborCell:Cell = new LiveCell(List())
    emptyNeighborCell.liveNeighborCount should equal(0)

    val multipleNeighborCell:Cell = new LiveCell(List(new DeadCell(List()),
                                                      new LiveCell(List()),
                                                      new LiveCell(List())))
    multipleNeighborCell.liveNeighborCount should equal(2)
  }
}
