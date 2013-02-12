package grid

import org.scalatest.{FunSpec}
import org.scalatest.matchers.ShouldMatchers
import cell.Cell

class GridTest extends FunSpec with ShouldMatchers {

  describe("A basic grid") {
    val subjectGrid:Grid = new Grid(2)
    val firstGeneration = subjectGrid.getCurrentGeneration

    it("should be able to create a nxn grid") {
      firstGeneration.length should equal(2)
      firstGeneration.foreach { row:Array[Cell] =>
        row.length should equal(2)
      }
    }

    it("should only have dead cells") {
      firstGeneration.foreach { row:Array[Cell] =>
        row.foreach(_.isLive should equal(false))
      }
    }

    it("should have all of its neighbors linked up properly") {
      assertNeighborsProperlyLinked(firstGeneration)
    }
  }

  describe("Adding live cells to the grid") {
    val subjectGrid:Grid = new Grid(2)
    subjectGrid.addLiveCell(0, 0)
    val firstGeneration = subjectGrid.getCurrentGeneration

    it("should have a live cell") {
      firstGeneration(0)(0).isLive should be(true)
    }

    it("should have all of its neighbors linked up properly") {
      assertNeighborsProperlyLinked(firstGeneration)
    }
  }

  def assertNeighborsProperlyLinked(arrayGrid: Array[Array[Cell]]) {
    arrayGrid.foreach { row: Array[Cell] =>
      row.foreach { cell: Cell =>
        cell.neighbors.foreach { neighborCell: Cell =>
          neighborCell.neighbors.contains(cell) should be(true)
        }
      }
    }
  }
}
