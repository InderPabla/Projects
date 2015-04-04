### Simple Stock Price Web Scrapper

First lets target a specific piece of information we want to get out of a site. In this case we want the stock price. Below we can see the price is between the span tags: 

![Alt text](https://github.com/InderPabla/Projects/blob/master/Web%20Scrapper%20-%20Stocks/Images/1.png "Optional Title")
![Alt text](https://github.com/InderPabla/Projects/blob/master/Web%20Scrapper%20-%20Stocks/Images/2.PNG "Optional Title")

Or suppose we wanted to get the name of company. Here we see the name is between the h2 tags: 

![Alt text](https://github.com/InderPabla/Projects/blob/master/Web%20Scrapper%20-%20Stocks/Images/3.png "Optional Title")
![Alt text](https://github.com/InderPabla/Projects/blob/master/Web%20Scrapper%20-%20Stocks/Images/4.PNG "Optional Title")

Python has a built in regular expression library which allows was very easy pattern matching. Only thing we need to focus on is “Some String”+”What We Want”+”Some Other String”. In the case of previous examples it was span and h2 tags. In python it would looks like this: 

![Alt text](https://github.com/InderPabla/Projects/blob/master/Web%20Scrapper%20-%20Stocks/Images/5.PNG "Optional Title")

(.+?) = dot matches all characters except a new line, plus matches one or more of characters, ? matching minimum about of characters. Note it would still work with (.) or (.+). Since we know for sure our match would be between h2 or span we want to match as less as possible. 
