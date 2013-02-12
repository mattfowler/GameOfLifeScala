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
      val nextGen = threeNeighborCell.getNextGeneration

      nextGen.isLive should be (true)
      nextGen.neighbors should equal(threeNeighbors)
    }

    it("should stay dead if it has anything other than three live neighbors") {
      val neighbors = List(new LiveCell(List()))
      val subjectCell = new DeadCell(neighbors)

      val subjectNextGen = subjectCell.getNextGeneration

      subjectNextGen.isLive should be (false)
      subjectNextGen.neighbors should equal(neighbors)

      val multipleNeighbors = List(new LiveCell(List()),
                                   new LiveCell(List()),
                                   new LiveCell(List()),
                                   new LiveCell(List()))

      val multiNeighborCell = new DeadCell(multipleNeighbors)
      val nextGen = multiNeighborCell.getNextGeneration

      nextGen.isLive should be (false)
      nextGen.neighbors should equal(multipleNeighbors)
    }
  }

}
