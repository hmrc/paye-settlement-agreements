PAYE Settlement Agreements
==========================

Microservice for PAYE Settlement Agreements (PSA). This implements the main business logic for PSA, communicating with ETMP(HOD) and Mongo Database for storage/retrieval. The microservice is based on the RESTful API structure, receives and sends data using JSON to either from.

[![Build Status](https://travis-ci.org/hmrc/paye-settlement-agreements.svg?branch=master)](https://travis-ci.org/hmrc/paye-settlement-agreements) [ ![Download](https://api.bintray.com/packages/hmrc/releases/paye-settlement-agreements/images/download.svg) ](https://bintray.com/hmrc/releases/paye-settlement-agreements/_latestVersion)


## Enrolment API

| PATH | Supported Methods | Description |
|------|-------------------|-------------|
|```/paye-settlement-agreements/enrol``` | POST | attempts to subscribe the user to PSA |


## Running Locally

Install [Service Manager](https://github.com/hmrc/service-manager), then start dependencies:

    sm --start PSA_ALL -f
    
At this point you will be running from a snapshot, to run PSA locally stop the snapshot:

    sm --stop PAYE_SETTLEMENT_AGREEMENTS

You can now browse to the root of your application and start the app:

    sbt "run 9505"


### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html")