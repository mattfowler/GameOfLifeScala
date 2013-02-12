package grid

import org.scalatest.{FunSpec, FlatSpec}
import org.scalatest.matchers.ShouldMatchers
import cell.Cell

class GridTest extends FunSpec with ShouldMatchers {

  describe("A basic grid") {
    val grid:Grid = new Grid(2)
    val firstGeneration = grid.getCurrentGeneration


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
      firstGeneration.foreach { row:Array[Cell] =>
        row.foreach { cell:Cell =>
          cell.neighbors.foreach { neighborCell:Cell =>
            neighborCell.neighbors.contains(cell) should be (true)
          }
        }
      }
    }
  }
}
