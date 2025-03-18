# FDA Drug Record API

## Overview

This is a Spring Boot application that exposes an API to interact with the Open FDA data on drug record applications. You can search for drug records, store them in your local database, and view stored records.

## Requirements

- JDK 11 or higher
- Maven
- H2 database (in memory)

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/kozakerro/DrugRecord.git

## Building the application:

2. Build the application:
   ```bash
   mvn clean install

3. Run the application:
   ```bash
   mvn spring-boot:run

## API Endpoints

    GET /api/drug-records/search?manufacturerName=<name>&brandName=<brand>&page=<page> - Search for drugs.
    POST /api/drug-records/<application_number> - Store a new drug record by its application number.
    GET /api/drug-records - Get all stored drug records.
    GET /api/drug-records/<application_number> - Get stored drug by its application number.
