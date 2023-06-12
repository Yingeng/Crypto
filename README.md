# Crypto - A Java Spring Boot Project

## Motivation
I'm interested exploring backend development, so I decided to learn Spring Boot, a production-grade framework for microservices. Cryto currency was a very popular topic, with BitCoins being the most talked about. I decided it would be nice to capture cryto currency data while learning Spring Boot.

## Overview
A personal project using Java Spring Boot to design a backend API to fetch candlestick crypto currency data from Binance API. Using backend database and in-memory cache to store API returned data to reduce repeated API request and latency.

## Technologies
* Spring Boot
  * basic backend framework
* MySQL
  * backend database to store API result
  * check database for request result before querying Binance API
* Mybatis
  * simplify database operation
* Redis
  * in-memory cache to reduce the latency and need to query database
* AWS
  * attemped to create EC2 instance to host Jenkins build jobs
* Jenkins
  * attempted to automatically build jobs for repo updates

## Future work
* get EC2 and Jenkins to work for automatic builds
* finish implementing unit and integration test
