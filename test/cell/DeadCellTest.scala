package cell

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class DeadCellTest extends FunSpec with ShouldMatchers {

  describe("A dead cell") {

    it("should zombify and turn live if it has three live neighbors") {
      val threeNeighbors = List(new LiveCell(List()),
                                new LiveCell(List()),
                                new DeadCell(List()),
                                new LiveCell(List()))
      val threeNeighborCell = new DeadCell(threeNeighbors)

      threeNeighborCell.getNextGeneration should equal (new LiveCell(threeNeighbors))
    }

    it("should stay dead if it has anything other than three live neighbors") {
      val neighbors = List(new LiveCell(List()))
      val subjectCell = new DeadCell(neighbors)

      subjectCell.getNextGeneration should equal (new DeadCell(neighbors))

      val multipleNeighbors = List(new LiveCell(List()),
                                   new LiveCell(List()),
                                   new LiveCell(List()),
                                   new LiveCell(List()))
      val multiNeighborCell = new DeadCell(multipleNeighbors)

      multiNeighborCell should equal (new DeadCell(multipleNeighbors))
    }
  }

}
