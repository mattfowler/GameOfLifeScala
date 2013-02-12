package cell

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

class EmptyCellTest extends FunSpec with ShouldMatchers {
  describe("An empty cell") {
    val emptyCell:EmptyCell = new EmptyCell(List())

    it("is not alive") {
      emptyCell.isLive should equal(false)
    }

    it("has a next generation that is always equal to itself") {
      emptyCell.getNextGeneration should equal(emptyCell)
    }
  }

}
