package csvResults

import java.io.PrintWriter

import experiments.{DistanceExperiment, WriteableAsCSV}

case class ResultsWriter(path: String, experiments: List[WriteableAsCSV]) extends PrintWriter(path){
  write(experiments.foldLeft("")((s,e) => s"$s${e.toCSV}\n"))
  close()
  println(s"\nResults written as CSV to file $path")
}