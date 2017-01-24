import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.io.Source

class YahooFinanceSpec extends FlatSpec with Matchers with BeforeAndAfter {

  var financialCalculator: FinancialCalculator = _

  before {
    val dataSample = Source.fromFile("./src/test/resources/data-2017-01-24.csv").getLines.toList
    financialCalculator = new FinancialCalculator(dataSample)
  }

  it should "download data from yahoo finance" in {
    val url = YahooFinance.createURL(java.time.LocalDate.of(2017, 1, 24), "GOOG")
    val dataFromURL = YahooFinance.dataFromURL(url)
    assert(dataFromURL.nonEmpty)
  }

  it should "read daily prices" in {
    val dailyPricesSample = Source.fromFile("./src/test/resources/prices-2017-01-24.csv")
      .getLines
      .map(_.toDouble)
      .toList
    financialCalculator.dailyPrices() should contain theSameElementsAs dailyPricesSample
  }

  it should "calculate daily returnes" in {
    val returnsSample = Source.fromFile("./src/test/resources/returns-2017-01-24.csv")
      .getLines
      .map(_.toDouble)
      .toList
    financialCalculator.returns() should contain theSameElementsAs returnsSample
  }

  it should "calculate mean return as arithmetic mean" in {
    val meanReturnSample = Source.fromFile("./src/test/resources/mean-2017-01-24.csv").mkString.toDouble
    assert(meanReturnSample == financialCalculator.meanReturn())
  }

  it should "calculate mean return as median" in {
    val medianReturnSample = Source.fromFile("./src/test/resources/median-2017-01-24.csv").mkString.toDouble
    assert(medianReturnSample == financialCalculator.medianReturn())
  }
}