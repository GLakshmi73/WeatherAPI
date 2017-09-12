# WeatherAPI

Written By Lakshmi G

Data retrieved from a opensource Weather RestFul API, and stores it in a Data store using the EHCache.
The Zipcodes are static hence stored in a file system. The Zipcodes data is stored in form of CSV.
Once the CSV is read and for each loop the API is hit and the results are stored in a Data Collector.
Now a Webservice is developed in same application to produce a webservice that acceps the Zipcode as param and retrieves Weather of that Zipcode from EHCache and returns it to the caller.
