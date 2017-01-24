class FinancialCalculator(rows: List[String]) {

  case class Day(date: String, open: Double, high: Double, low: Double, close: Double, volume: Long, adjClose: Double)

  var data: List[Day] = rows.map {
    line =>
      val splitLine = line.split(",")
      Day(splitLine(0),
        splitLine(1).toDouble,
        splitLine(2).toDouble,
        splitLine(3).toDouble,
        splitLine(4).toDouble,
        splitLine(5).toLong,
        splitLine(6).toDouble)
  }

  def dailyPrices(): List[Double] = data.map(_.close)

  def returns(): List[Double] = {
    val i = data.sliding(2).map {
      case List(today, yesterday) => (yesterday.close - today.close) / yesterday.close
    }
    i.toList
  }

  def meanReturn(): Double = {
    val r = returns()
    r.sum / r.size
  }

  def medianReturn(): Double = {
    val r = returns()
    val (lower, upper) = r.sortWith(_ < _).splitAt(r.size / 2)
    if (r.size % 2 == 0)
      (lower.last + upper.head) / 2.0
    else
      upper.head
  }
}
