package pgrela.eulerproject.problem111

import java.util.stream.LongStream

import pgrela.eulerproblem.common.{Maths, Primes, SolutionRunner, EulerSolver}

object Solution extends EulerSolver{
  def main(args:Array[String]):Unit ={
    SolutionRunner.printSolution(this)
  }

  override def solve(): Long ={
    val primes: Array[Int] = Primes.primes(100000).toArray
      5
  }
}
