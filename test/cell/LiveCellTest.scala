package cell

import org.scalatest.{FunSpec, FlatSpec}
import org.scalatest.matchers.ShouldMatchers

class LiveCellTest extends FunSpec with ShouldMatchers {

  describe("A live cell") {

    it("should die if has insufficient live neighbors") {
      val neighbors = List(new LiveCell(List()), new DeadCell(List()))
      val subjectCell = new LiveCell(neighbors)

      subjectCell.getNextGeneration should equal (new DeadCell(neighbors))
    }

    it("should die if it has too many live neighbors") {
      val neighbors = List(new LiveCell(List()),
                           new LiveCell(List()),
                           new LiveCell(List()),
                           new LiveCell(List()))
      val subjectCell = new LiveCell(neighbors)

      subjectCell.getNextGeneration should equal (new DeadCell(neighbors))
    }

    it("should live if it has two or three neighbors") {
      val threeNeighbors = List(new LiveCell(List()),
                                new LiveCell(List()),
                                new DeadCell(List()),
                                new LiveCell(List()))
      val threeNeighborCell = new LiveCell(threeNeighbors)

      threeNeighborCell.getNextGeneration should equal (new LiveCell(threeNeighbors))

      val twoNeighbors = List(new LiveCell(List()),
                              new DeadCell(List()),
                              new DeadCell(List()),
                              new LiveCell(List()))
      val twoNeighborCell = new LiveCell(twoNeighbors)

      twoNeighborCell.getNextGeneration should equal (new LiveCell(twoNeighbors))

    }
  }
}
