# College Tuition Fee Predictor
## Get Future College tuition Fee and All College Details - USA


## Features

- Uses a 2019-2020 Official College Data from https://collegescorecard.ed.gov/data/
- Uses static data source (csv) files which has massaged data required for the APIs
- Provides State-wise Institution-type based average tuition fee data
- Provides specific college tuition fee based on in-state and out of state input
- Provides future tuition fee of colleges based on college specific inflation rates


## Tech

- Spring Boot
- Data JPA
- Hibernate
- Java 11


## Execution
```sh
Install the maven dependencies and build the application
```

## API endpoints

http://localhost:8080/api/v1/collegeData/getTuitionFee
http://localhost:8080/api/v1/collegeData/getInflationRate/csv/
http://localhost:8080/api/v1/collegeData/getCollegeNames/csv
