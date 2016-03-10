package pgrela.eulerproject.problem111

import pgrela.eulerproblem.common.EulerSolver
import pgrela.eulerproblem.common.SolutionRunner._

class Problem extends EulerSolver with App{
  override def main(args: Array[String]) {
    printSolution(classOf[Problem])
  }


  override val executionStart: Long = 1


  override def solve(): Long = {
    4
  }
}
