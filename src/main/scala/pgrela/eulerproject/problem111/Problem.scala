package pgrela.eulerproject.problem111

import pgrela.eulerproblem.common.EulerSolver
import pgrela.eulerproblem.common.SolutionRunner._

object Problem extends EulerSolver with App{
  override def main(args: Array[String]) {
    printSolution(this.getClass)
  }

  override def solve(): Long = {
    4
  }
}
