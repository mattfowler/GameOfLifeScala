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

  describe("Setting live cells on the grid") {
    val subjectGrid:Grid = new Grid(2)
    subjectGrid.setLiveCellAt(0, 0)
    val firstGeneration = subjectGrid.getCurrentGeneration

    it("should have a live cell") {
      firstGeneration(0)(0).isLive should be(true)
    }

    it("should have all of its neighbors linked up properly") {
      assertNeighborsProperlyLinked(firstGeneration)
    }
  }

  describe("Setting dead cells on the grid") {
    val subjectGrid:Grid = new Grid(2)
    subjectGrid.setLiveCellAt(0, 0)
    subjectGrid.setDeadCellAt(0, 0)
    val firstGeneration = subjectGrid.getCurrentGeneration

    it("should have a dead cell") {
      firstGeneration(0)(0).isLive should be(false)
    }

    it("should have all of its neighbors linked up properly") {
      assertNeighborsProperlyLinked(firstGeneration)
    }
  }

  describe("Playing the game of life with one live column in the middle of the grid") {
    val subjectGrid:Grid = new Grid(3)

    subjectGrid.setLiveCellAt(0, 1)
    subjectGrid.setLiveCellAt(1, 1)
    subjectGrid.setLiveCellAt(2, 1)

    it("should show the first generation as a line of live cells on row two") {
      val nextGen = subjectGrid.getNextGeneration

      nextGen(0).foreach(_.isLive should be (false))
      nextGen(1).foreach(_.isLive should be (true))
      nextGen(2).foreach(_.isLive should be (false))

      assertNeighborsProperlyLinked(nextGen)
    }

    it ("should show the second generation as a line of live cells on column two") {
      val nextGen = subjectGrid.getNextGeneration

      val transposedGen = nextGen.transpose
      transposedGen(0).foreach(_.isLive should be (false))
      transposedGen(1).foreach(_.isLive should be (true))
      transposedGen(2).foreach(_.isLive should be (false))

      assertNeighborsProperlyLinked(nextGen)
    }

    it("should show the third generation as a line of live cells on row two") {
      val nextGen = subjectGrid.getNextGeneration

      nextGen(0).foreach(_.isLive should be (false))
      nextGen(1).foreach(_.isLive should be (true))
      nextGen(2).foreach(_.isLive should be (false))

      assertNeighborsProperlyLinked(nextGen)
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
