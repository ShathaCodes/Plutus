# Fraud Application

## Overview 

Detects whether an `exchange transaction` is fraudulent.


## Getting Started

Install `pipenv` to run the project in a virtual environment

Run the command below to install dependencies
```bash
pipenv install
```

To spawn the shell within the venv run :

```bash
pipenv shell
```

To run the project in development mode :

```bash
flask --app main.py run
```

## API

**URI** : `/transactions/verify`

**HTTP Method**: GET

```json
{
	"amount": 500,
	"timestamp": "2018-11-13T20:20:39+00:00",
	"senderId": 1,
	"receiverId": 2
}
```

As a response we get 0/1 (whether it's fraudulent or not)

```json
{
    "is_fraud": 0
}
```