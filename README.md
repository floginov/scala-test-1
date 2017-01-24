# YahooFinance
This is the main class. Run it to see an example of the application.

# FinancialCalculator
It is the class which store ticker's data and does needed calculations.
* dailyPrices() - assume that daily price is a "close" price;
* returns() - rows in Yahoo Finance file are arranged in descending order by day, so we dont need to sort them;
* meanReturn() - as arithmetic mean;
* medianReturn() - as median;

# YahooFinanceSpec
It is the scalatest class. It used for testing of application.
* ./src/test/resources/ - contains correct output data