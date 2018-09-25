[![Build Status](https://travis-ci.org/felizi/mutant-service.svg?branch=develop)](https://travis-ci.org/felizi/mutant-service)
[![codecov](https://codecov.io/gh/felizi/mutant-service/branch/develop/graph/badge.svg)](https://codecov.io/gh/felizi/mutant-service)
# API for Detection of Mutants

### Stack
- Spring Boot
- Java 8
- H2
- Gradle

### Checkout, build and run
- Checkout the [source code](https://github.com/felizi/mutant-service)
- Build: ```gradle clean build```
- Run: ```java -jar ./build/libs/mutant-service-0.0.1-SNAPSHOT.jar```


## Tests
API /mutant/
-----
#### DNA of MUTANT
##### Request
```
POST http://mutant-service.herokuapp.com/mutant/

POST data:
{
  "dna": [
    "GAATCG",
    "GATTTG",
    "TGATAG",
    "CCAGTG",
    "CTTGCG",
    "AATCTG"
  ]
}
```
##### Response
```
{
  "content": "MUTANT",
  "_links": {
    "self": {
      "href": "http://mutant-service.herokuapp.com/mutant/"
    },
    "stat": {
      "href": "http://mutant-service.herokuapp.com/stats/"
    }
  }
}
```
#### DNA of HUMAN
##### Request
```
POST http://mutant-service.herokuapp.com/mutant/

POST data:
{
  "dna": [
    "AGTCCC",
    "GTCTAT",
    "CGCCGC",
    "CACCTT",
    "ATGCGG",
    "AGGAGG"
  ]
}
```
##### Response
```
{
  "content": "HUMAN",
  "_links": {
    "self": {
      "href": "http://mutant-service.herokuapp.com/mutant/"
    },
    "stat": {
      "href": "http://mutant-service.herokuapp.com/stats/"
    }
  }
}
```
API /stats/
-----
#### Stats
##### Request
```
GET http://mutant-service.herokuapp.com/stats/
```
##### Response
```
{
    "count_mutant_dna": 0,
    "count_human_dna": 0,
    "ratio": 1
}
```
