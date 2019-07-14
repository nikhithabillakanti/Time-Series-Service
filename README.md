# Time-Series-Service

## Build
mvn install

## Run
mvn spring-boot:run

## APIs

Insert new Time series:

PUT http://localhost:8080/timeseries (body as description)

Filter by Day:

Get http://localhost:8080/timeseries?day=<yyyy-mm-dd> 
example 
http://localhost:8080/timeseries?day=2018-08-09

Filter by Week:

Get http://localhost:8080/timeseries?week=<week in year> 
example 
http://localhost:8080/timeseries?week=32

Total for one week and all meters:

http://localhost:8080/timeseries/customer/<customer_id>/all
example
http://localhost:8080/timeseries/customer/nsd98f/all

Sum weekly per meter:

http://localhost:8080/timeseries/customer/<customer_id>
example
http://localhost:8080/timeseries/customer/nsd98f
