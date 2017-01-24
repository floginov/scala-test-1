import scala.io.Source

object YahooFinance {

  def main(args: Array[String]) {

    /* Set business date and ticker*/
    val businessDate = java.time.LocalDate.now()
    val ticker = "GOOG"

    /* Read data from Yahoo Finance*/
    val url = createURL(businessDate, ticker)
    val rows = dataFromURL(url)

    /* Create Financial Calculator*/
    val financialCalculator = new FinancialCalculator(rows)

    /* Calculate daily prices*/
    val resDailyPrices = financialCalculator.dailyPrices()
    println("dailyPrices:")
    resDailyPrices foreach println

    /* Calculate daily returns*/
    val resReturnes = financialCalculator.returns()
    println("returnes:")
    resReturnes foreach println

    /* Calculate mean (arithmetic mean) return*/
    val resMeanReturn = financialCalculator.meanReturn()
    println(s"meanReturn:\n $resMeanReturn")

    /* Calculate median return*/
    val resMedianReturn = financialCalculator.medianReturn()
    println(s"meanReturn:\n $resMedianReturn")
  }

  def createURL(businessDate: java.time.LocalDate, ticker: String): String = {
    val lastYear = businessDate.minusYears(1)
    val url = f"http://real-chart.finance.yahoo.com/table.csv?s=$ticker&a=${lastYear.getMonthValue}&b=${lastYear.getDayOfMonth}&c=${lastYear.getYear}&d=${businessDate.getMonthValue}&e=${businessDate.getDayOfMonth}&f=${businessDate.getYear}&g=d&ignore=.csv"
    url
  }

  def dataFromURL(url: String): List[String] = {
    val html = Source.fromURL(url)
    val data = html.getLines().drop(1).toList
    data
  }
}