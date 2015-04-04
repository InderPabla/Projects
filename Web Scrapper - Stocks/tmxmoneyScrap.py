import urllib #accessing website
import re #regex patter matching
import sys #compile time arguments

#using site http://web.tmxmoney.com
url_stock = ["http://web.tmxmoney.com/quote.php?qm_symbol=","&locale=EN&src=topq&mobile=false"]

regex_stock = '<span>(.+?)</span>' #regex pattern match to find stock price within span tag
regex_name = '<h2>(.+?)</h2>' #regex pattern match to find company name within header h2 tag

#compile with regex library
pattern_stock = re.compile(regex_stock) 
pattern_name = re.compile(regex_name) 

x = 1 #skipping first argument, because first argument is name of file
while (x<len(sys.argv)):
    stock_id = sys.argv[x] #get argument at x
    try: #in case site does not exist (user entered in wrong market name)
        html_file = urllib.urlopen(url_stock[0]+stock_id+url_stock[1]) #get html file of the site
        html_text = html_file.read() #read file

        stock = re.findall(pattern_stock,html_text) #find and put all instances of span tags an array 
        print stock[0] #print first index, since it is stock price

        name = re.findall(pattern_name,html_text) #find and put all instances of h2 tags an array 
        print name[0] #print first index, since it is company name
        
    except Exception: #if site did not exists, it means market name was incorrect
        
        #Error printed twice because Scrapper.php is reading twice, first for price and second for company name.
        #Printing on two lines is necessary in order to not generate index out of bounds error in Scrapper.php.
        print "error "+sys.argv[x]+"\nerror" #print error CompanyName \n error
        
        pass 
    x = x+1 #increment x
