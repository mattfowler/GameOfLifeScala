package cell

import org.scalatest.{FunSpec, FlatSpec}
import org.scalatest.matchers.ShouldMatchers

class LiveCellTest extends FunSpec with ShouldMatchers {

  describe("A live cell") {

    it("should die if has insufficient live neighbors") {
      val neighbors = List(new LiveCell(List()), new DeadCell(List()))
      val subjectCell = new LiveCell(neighbors)

      val nextGen = subjectCell.getNextGeneration

      nextGen.isLive should be (false)
      nextGen.neighbors should equal(neighbors)
    }

    it("should die if it has too many live neighbors") {
      val neighbors = List(new LiveCell(List()),
                           new LiveCell(List()),
                           new LiveCell(List()),
                           new LiveCell(List()))
      val subjectCell = new LiveCell(neighbors)

      val nextGen = subjectCell.getNextGeneration

      nextGen.isLive should be (false)
      nextGen.neighbors should equal(neighbors)
    }

    it("should live if it has two or three neighbors") {
      val threeNeighbors = List(new LiveCell(List()),
                                new LiveCell(List()),
                                new DeadCell(List()),
                                new LiveCell(List()))
      val threeNeighborCell = new LiveCell(threeNeighbors)

      val nextGen = threeNeighborCell.getNextGeneration

      nextGen.isLive should be (true)
      nextGen.neighbors should equal(threeNeighbors)


      val twoNeighbors = List(new LiveCell(List()),
                              new DeadCell(List()),
                              new DeadCell(List()),
                              new LiveCell(List()))
      val twoNeighborCell = new LiveCell(twoNeighbors)

      val twoNeighborNextGen = twoNeighborCell.getNextGeneration

      twoNeighborNextGen.isLive should be (true)
      twoNeighborNextGen.neighbors should equal(twoNeighbors)
    }
  }
}
